package com.tw.joi.delivery.dto.response;

import com.tw.joi.delivery.enums.InventoryStatus;

/**
 * Inventory health response for a store.
 *
 * @param storeId store identifier
 * @param storeName store name
 * @param totalProducts total products in inventory
 * @param lowStockCount products below threshold
 * @param outOfStockCount products out of stock
 * @param status inventory status
 */
public record InventoryHealthResponse(
    String storeId,
    String storeName,
    int totalProducts,
    int lowStockCount,
    int outOfStockCount,
    InventoryStatus status) {}
