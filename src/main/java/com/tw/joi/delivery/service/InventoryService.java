package com.tw.joi.delivery.service;

import com.tw.joi.delivery.domain.GroceryProduct;
import com.tw.joi.delivery.domain.GroceryStore;
import com.tw.joi.delivery.dto.response.InventoryHealthResponse;
import com.tw.joi.delivery.enums.InventoryStatus;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final ProductService productService;
    private final StoreService storeService;

    public InventoryHealthResponse fetchStoreInventoryHealth(String storeId) {
        GroceryStore store = storeService.fetchStoreById(storeId);
        List<GroceryProduct> products = productService.getProductsByStoreId(storeId);
        InventoryStats stats = calculateInventoryStats(products);
        return new InventoryHealthResponse(
            store.getOutletId(),
            store.getName(),
            stats.totalProducts(),
            stats.lowStockCount(),
            stats.outOfStockCount(),
            determineStatus(stats.lowStockCount(), stats.outOfStockCount())
        );
    }

    private InventoryStats calculateInventoryStats(List<GroceryProduct> products) {
        int total = products.size();
        int lowStock = 0;
        int outOfStock = 0;
        for (GroceryProduct product : products) {
            if (product.getAvailableStock() <= 0) {
                outOfStock++;
            } else if (product.getAvailableStock() <= product.getThreshold()) {
                lowStock++;
            }
        }
        return new InventoryStats(total, lowStock, outOfStock);
    }

    private InventoryStatus determineStatus(int lowStock, int outOfStock) {
        if (outOfStock > 0) {
            return InventoryStatus.OUT_OF_STOCK;
        }
        if (lowStock > 0) {
            return InventoryStatus.LOW_STOCK;
        }
        return InventoryStatus.HEALTHY;
    }

    private record InventoryStats(int totalProducts, int lowStockCount, int outOfStockCount) {
    }
}
