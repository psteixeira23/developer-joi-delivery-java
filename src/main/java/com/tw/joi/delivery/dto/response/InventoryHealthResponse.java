package com.tw.joi.delivery.dto.response;

import com.tw.joi.delivery.enums.InventoryStatus;

public record InventoryHealthResponse(
    String storeId,
    String storeName,
    int totalProducts,
    int lowStockCount,
    int outOfStockCount,
    InventoryStatus status
) {
}
