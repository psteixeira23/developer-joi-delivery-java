package com.tw.joi.delivery.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.tw.joi.delivery.domain.GroceryStore;
import com.tw.joi.delivery.exception.NotFoundException;
import com.tw.joi.delivery.seed.SeedData;
import com.tw.joi.delivery.testutil.TestConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StoreServiceTest {

    private StoreService storeService;

    @BeforeEach
    void setUp() {
        SeedData.reset();
        storeService = new StoreService();
    }

    @Test
    void shouldReturnStoreWhenExists() {
        GroceryStore store = storeService.fetchStoreById(TestConstants.STORE_ID_101);
        assertEquals(TestConstants.STORE_ID_101, store.getOutletId());
    }

    @Test
    void shouldThrowWhenStoreDoesNotExist() {
        assertThrows(NotFoundException.class,
            () -> storeService.fetchStoreById(TestConstants.STORE_ID_MISSING));
    }
}
