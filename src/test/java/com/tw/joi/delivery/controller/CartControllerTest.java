package com.tw.joi.delivery.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.tw.joi.delivery.domain.Cart;
import com.tw.joi.delivery.domain.GroceryProduct;
import com.tw.joi.delivery.dto.request.AddProductRequest;
import com.tw.joi.delivery.dto.response.CartProductInfo;
import com.tw.joi.delivery.exception.GlobalExceptionHandler;
import com.tw.joi.delivery.exception.NotFoundException;
import com.tw.joi.delivery.service.CartService;
import com.tw.joi.delivery.testutil.TestConstants;
import com.tw.joi.delivery.testutil.TestFixtures;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/** Tests for cart endpoints. */
@WebMvcTest(CartController.class)
@org.springframework.context.annotation.Import(GlobalExceptionHandler.class)
public class CartControllerTest {

  @Autowired private MockMvc mockMvc;

  @MockitoBean private CartService cartService;

  ObjectMapper mapper = new ObjectMapper();

  @Test
  void shouldAddTheRequestedProductToTheCart() throws Exception {

    String url = "/cart/product";
    AddProductRequest addProductRequest = TestFixtures.addProductRequest101();

    ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
    String requestJson = ow.writeValueAsString(addProductRequest);

    Cart cart = Cart.builder().cartId(TestConstants.CART_ID_101).build();
    GroceryProduct product = TestFixtures.product101(TestFixtures.store101());
    when(cartService.addProductToCartForUser(addProductRequest))
        .thenReturn(new CartProductInfo(cart, product, product.getSellingPrice()));

    mockMvc
        .perform(
            MockMvcRequestBuilders.post(url)
                .content(requestJson)
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }

  @Test
  void shouldReturnTheCart() throws Exception {
    String url = "/cart/view?userId={userId}";
    String userId = TestConstants.USER_ID_101;
    Cart cart = Cart.builder().cartId(TestConstants.CART_ID_101).build();
    when(cartService.getCartForUser(userId)).thenReturn(cart);

    mockMvc
        .perform(
            MockMvcRequestBuilders.get(url, TestConstants.USER_ID_101)
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.cartId", Is.is(TestConstants.CART_ID_101)));
  }

  @Test
  void shouldReturnBadRequestWhenMissingFields() throws Exception {
    String url = "/cart/product";
    mockMvc
        .perform(
            MockMvcRequestBuilders.post(url).content("{}").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest())
        .andExpect(MockMvcResultMatchers.jsonPath("$.code", Is.is("VALIDATION_ERROR")));
  }

  @Test
  void shouldReturnNotFoundWhenUserDoesNotExist() throws Exception {
    String url = "/cart/view?userId={userId}";
    String userId = TestConstants.USER_ID_MISSING;
    when(cartService.getCartForUser(userId))
        .thenThrow(new NotFoundException("User not found for userId=" + userId));

    mockMvc
        .perform(MockMvcRequestBuilders.get(url, userId).contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound())
        .andExpect(MockMvcResultMatchers.jsonPath("$.code", Is.is("NOT_FOUND")));
  }

  @Test
  void shouldReturnBadRequestWhenUserIdIsBlank() throws Exception {
    String url = "/cart/view?userId=";
    mockMvc
        .perform(MockMvcRequestBuilders.get(url).contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest())
        .andExpect(MockMvcResultMatchers.jsonPath("$.code", Is.is("VALIDATION_ERROR")));
  }
}
