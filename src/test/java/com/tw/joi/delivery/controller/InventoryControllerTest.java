package com.tw.joi.delivery.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.tw.joi.delivery.dto.response.InventoryHealthResponse;
import com.tw.joi.delivery.exception.GlobalExceptionHandler;
import com.tw.joi.delivery.exception.NotFoundException;
import com.tw.joi.delivery.service.InventoryService;
import com.tw.joi.delivery.testutil.TestConstants;
import com.tw.joi.delivery.testutil.TestFixtures;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(InventoryController.class)
@org.springframework.context.annotation.Import(GlobalExceptionHandler.class)
class InventoryControllerTest {

  @Autowired private MockMvc mockMvc;

  @MockitoBean private InventoryService inventoryService;

  @Test
  void shouldReturnTheHealthOfTheStore() throws Exception {
    String getUrl = "/inventory/health?storeId={storeId}";
    InventoryHealthResponse response = TestFixtures.healthyInventoryResponse();
    when(inventoryService.fetchStoreInventoryHealth(TestConstants.STORE_ID_101))
        .thenReturn(response);
    mockMvc
        .perform(
            MockMvcRequestBuilders.get(getUrl, TestConstants.STORE_ID_101)
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.status", org.hamcrest.core.Is.is("HEALTHY")))
        .andExpect(MockMvcResultMatchers.jsonPath("$.totalProducts", org.hamcrest.core.Is.is(3)));
  }

  @Test
  void shouldReturnNotFoundWhenStoreDoesNotExist() throws Exception {
    String getUrl = "/inventory/health?storeId={storeId}";
    when(inventoryService.fetchStoreInventoryHealth(TestConstants.STORE_ID_MISSING))
        .thenThrow(
            new NotFoundException("Store not found for storeId=" + TestConstants.STORE_ID_MISSING));

    mockMvc
        .perform(
            MockMvcRequestBuilders.get(getUrl, TestConstants.STORE_ID_MISSING)
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound())
        .andExpect(MockMvcResultMatchers.jsonPath("$.code", org.hamcrest.core.Is.is("NOT_FOUND")));
  }

  @Test
  void shouldReturnBadRequestWhenStoreIdIsBlank() throws Exception {
    String getUrl = "/inventory/health?storeId=";
    mockMvc
        .perform(MockMvcRequestBuilders.get(getUrl).contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest())
        .andExpect(
            MockMvcResultMatchers.jsonPath("$.code", org.hamcrest.core.Is.is("VALIDATION_ERROR")));
  }
}
