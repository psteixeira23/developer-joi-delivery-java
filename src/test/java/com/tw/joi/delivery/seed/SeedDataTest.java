package com.tw.joi.delivery.seed;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.tw.joi.delivery.domain.Cart;
import com.tw.joi.delivery.testutil.TestConstants;
import org.junit.jupiter.api.Test;

class SeedDataTest {

    @Test
    void shouldInitializeSeedDataConsistently() {
        SeedData.reset();
        assertEquals(2, SeedData.getUsers().size());
        assertEquals(2, SeedData.getCartForUsers().size());
        Cart cart = SeedData.getCartForUsers().get(TestConstants.USER_ID_101);
        assertNotNull(cart);
        assertEquals(TestConstants.CART_ID_101, cart.getCartId());
        assertEquals(TestConstants.STORE_ID_101, cart.getOutlet().getOutletId());
        assertNotNull(SeedData.getUser101().getCart());
    }
}
