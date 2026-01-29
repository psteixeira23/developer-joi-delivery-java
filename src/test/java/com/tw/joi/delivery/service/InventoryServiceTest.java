package com.tw.joi.delivery.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.tw.joi.delivery.domain.GroceryProduct;
import com.tw.joi.delivery.dto.response.InventoryHealthResponse;
import com.tw.joi.delivery.enums.InventoryStatus;
import com.tw.joi.delivery.seed.SeedData;
import com.tw.joi.delivery.testutil.TestConstants;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InventoryServiceTest {

    private InventoryService inventoryService;

    @BeforeEach
    void setUp() {
        SeedData.reset();
        inventoryService = new InventoryService(new ProductService(), new StoreService());
    }

    @Test
    void shouldReturnHealthyStatusWhenAllProductsAboveThreshold() {
        InventoryHealthResponse response = inventoryService.fetchStoreInventoryHealth(TestConstants.STORE_ID_101);
        assertEquals(InventoryStatus.HEALTHY, response.status());
        assertEquals(3, response.totalProducts());
        assertEquals(0, response.lowStockCount());
        assertEquals(0, response.outOfStockCount());
    }

    @Test
    void shouldReturnLowStockWhenAnyProductBelowThreshold() {
        List<GroceryProduct> products = SeedData.getGroceryProducts();
        products.get(0).setAvailableStock(5);
        InventoryHealthResponse response = inventoryService.fetchStoreInventoryHealth(TestConstants.STORE_ID_101);
        assertEquals(InventoryStatus.LOW_STOCK, response.status());
        assertEquals(1, response.lowStockCount());
    }

    @Test
    void shouldReturnOutOfStockWhenAnyProductIsUnavailable() {
        List<GroceryProduct> products = SeedData.getGroceryProducts();
        products.get(0).setAvailableStock(0);
        InventoryHealthResponse response = inventoryService.fetchStoreInventoryHealth(TestConstants.STORE_ID_101);
        assertEquals(InventoryStatus.OUT_OF_STOCK, response.status());
        assertEquals(1, response.outOfStockCount());
    }
}
