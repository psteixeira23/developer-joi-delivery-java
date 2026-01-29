package com.tw.joi.delivery.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.tw.joi.delivery.testutil.TestConstants;
import com.tw.joi.delivery.testutil.TestFixtures;
import org.junit.jupiter.api.Test;

/** Tests for basic domain wiring. */
class DomainModelTest {

  @Test
  void shouldCreateAndLinkDomainObjects() {
    GroceryStore store = TestFixtures.store101();

    GroceryProduct product = TestFixtures.product101(store);

    Cart cart = TestFixtures.cart101(store);
    cart.addProduct(product);

    User user = TestFixtures.user101(cart);
    assertEquals(TestConstants.USER_ID_101, user.getUserId());
    assertEquals(TestConstants.STORE_ID_101, store.getOutletId());
    assertEquals(TestConstants.PRODUCT_ID_101, product.getProductId());
    assertEquals(1, cart.getProducts().size());
  }
}
