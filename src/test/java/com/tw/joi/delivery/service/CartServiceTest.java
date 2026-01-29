package com.tw.joi.delivery.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.tw.joi.delivery.domain.Cart;
import com.tw.joi.delivery.dto.request.AddProductRequest;
import com.tw.joi.delivery.dto.response.CartProductInfo;
import com.tw.joi.delivery.exception.NotFoundException;
import com.tw.joi.delivery.seed.SeedData;
import com.tw.joi.delivery.testutil.TestConstants;
import com.tw.joi.delivery.testutil.TestFixtures;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CartServiceTest {

    private CartService cartService;

    @BeforeEach
    void setUp() {
        SeedData.reset();
        cartService = new CartService(new UserService(), new ProductService());
    }

    @Test
    void shouldAddProductToCart() {
        AddProductRequest request = TestFixtures.addProductRequest101();

        Cart cartBefore = cartService.getCartForUser(TestConstants.USER_ID_101);
        int initialSize = cartBefore.getProducts().size();

        CartProductInfo result = cartService.addProductToCartForUser(request);
        assertEquals(TestConstants.PRODUCT_ID_101, result.product().getProductId());
        assertEquals(initialSize + 1, result.cart().getProducts().size());
    }

    @Test
    void shouldReturnCartForUser() {
        Cart cart = cartService.getCartForUser(TestConstants.USER_ID_101);
        assertEquals(TestConstants.CART_ID_101, cart.getCartId());
    }

    @Test
    void shouldThrowWhenUserDoesNotExist() {
        AddProductRequest request = new AddProductRequest();
        request.setUserId(TestConstants.USER_ID_MISSING);
        request.setOutletId(TestConstants.STORE_ID_101);
        request.setProductId(TestConstants.PRODUCT_ID_101);

        assertThrows(NotFoundException.class, () -> cartService.addProductToCartForUser(request));
    }

    @Test
    void shouldThrowWhenProductDoesNotExist() {
        AddProductRequest request = new AddProductRequest();
        request.setUserId(TestConstants.USER_ID_101);
        request.setOutletId(TestConstants.STORE_ID_101);
        request.setProductId(TestConstants.PRODUCT_ID_MISSING);

        assertThrows(NotFoundException.class, () -> cartService.addProductToCartForUser(request));
    }

    @Test
    void shouldThrowWhenCartDoesNotExist() {
        SeedData.removeCartForUser(TestConstants.USER_ID_101);
        assertThrows(NotFoundException.class, () -> cartService.getCartForUser(TestConstants.USER_ID_101));
    }
}
