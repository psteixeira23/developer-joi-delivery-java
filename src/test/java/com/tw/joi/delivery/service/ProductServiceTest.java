package com.tw.joi.delivery.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.tw.joi.delivery.domain.GroceryProduct;
import com.tw.joi.delivery.exception.NotFoundException;
import com.tw.joi.delivery.seed.SeedData;
import com.tw.joi.delivery.testutil.TestConstants;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ProductServiceTest {

  private ProductService productService;

  @BeforeEach
  void setUp() {
    SeedData.reset();
    productService = new ProductService();
  }

  @Test
  void shouldReturnProductForStore() {
    GroceryProduct product =
        productService.getProduct(TestConstants.PRODUCT_ID_101, TestConstants.STORE_ID_101);
    assertEquals(TestConstants.PRODUCT_ID_101, product.getProductId());
  }

  @Test
  void shouldThrowWhenProductNotFoundForStore() {
    assertThrows(
        NotFoundException.class,
        () -> productService.getProduct(TestConstants.PRODUCT_ID_101, TestConstants.STORE_ID_102));
  }

  @Test
  void shouldReturnProductsByStore() {
    List<GroceryProduct> products = productService.getProductsByStoreId(TestConstants.STORE_ID_101);
    assertEquals(3, products.size());
  }
}
