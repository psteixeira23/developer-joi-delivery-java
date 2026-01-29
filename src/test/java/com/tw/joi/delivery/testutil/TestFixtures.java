package com.tw.joi.delivery.testutil;

import com.tw.joi.delivery.domain.Cart;
import com.tw.joi.delivery.domain.GroceryProduct;
import com.tw.joi.delivery.domain.GroceryStore;
import com.tw.joi.delivery.domain.User;
import com.tw.joi.delivery.dto.request.AddProductRequest;
import com.tw.joi.delivery.dto.response.InventoryHealthResponse;
import com.tw.joi.delivery.enums.InventoryStatus;
import java.math.BigDecimal;

/** Factory helpers for test objects. */
public final class TestFixtures {

  private TestFixtures() {}

  /**
   * Creates a default grocery store fixture.
   *
   * @return grocery store
   */
  public static GroceryStore store101() {
    return GroceryStore.builder().name("Fresh Picks").outletId(TestConstants.STORE_ID_101).build();
  }

  /**
   * Creates a default grocery product fixture.
   *
   * @param store store reference
   * @return grocery product
   */
  public static GroceryProduct product101(GroceryStore store) {
    return GroceryProduct.builder()
        .productId(TestConstants.PRODUCT_ID_101)
        .productName("Wheat Bread")
        .mrp(BigDecimal.valueOf(10.5))
        .weight(BigDecimal.valueOf(500))
        .threshold(10)
        .availableStock(30)
        .store(store)
        .build();
  }

  /**
   * Creates a default cart fixture.
   *
   * @param store store reference
   * @return cart
   */
  public static Cart cart101(GroceryStore store) {
    return Cart.builder().cartId(TestConstants.CART_ID_101).outlet(store).build();
  }

  /**
   * Creates a default user fixture.
   *
   * @param cart cart reference
   * @return user
   */
  public static User user101(Cart cart) {
    return User.builder()
        .userId(TestConstants.USER_ID_101)
        .firstName("John")
        .lastName("Doe")
        .email("john.doe@gmail.com")
        .phoneNumber("900000000")
        .cart(cart)
        .build();
  }

  /**
   * Creates a request to add the default product to the default cart.
   *
   * @return add product request
   */
  public static AddProductRequest addProductRequest101() {
    AddProductRequest request = new AddProductRequest();
    request.setUserId(TestConstants.USER_ID_101);
    request.setOutletId(TestConstants.STORE_ID_101);
    request.setProductId(TestConstants.PRODUCT_ID_101);
    return request;
  }

  /**
   * Creates a healthy inventory response fixture.
   *
   * @return inventory health response
   */
  public static InventoryHealthResponse healthyInventoryResponse() {
    return new InventoryHealthResponse(
        TestConstants.STORE_ID_101, "Fresh Picks", 3, 0, 0, InventoryStatus.HEALTHY);
  }
}
