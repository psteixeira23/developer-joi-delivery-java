package com.tw.joi.delivery.service;

import com.tw.joi.delivery.domain.GroceryProduct;
import com.tw.joi.delivery.exception.NotFoundException;
import com.tw.joi.delivery.seed.SeedData;
import java.util.List;
import java.util.Objects;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final List<GroceryProduct> products = SeedData.getGroceryProducts();

    public GroceryProduct getProduct(String productId, String outletId) {
        return products.stream()
            .filter(groceryProduct -> isSameProductInStore(groceryProduct, productId, outletId))
            .findFirst()
            .orElseThrow(() -> new NotFoundException(
                "Product not found for outletId=%s and productId=%s".formatted(outletId, productId)));
    }

    public List<GroceryProduct> getProductsByStoreId(String storeId) {
        return products.stream()
            .filter(product -> isProductInStore(product, storeId))
            .toList();
    }

    private boolean isSameProductInStore(GroceryProduct groceryProduct, String productId, String outletId) {
        return Objects.equals(groceryProduct.getProductId(), productId)
            && isProductInStore(groceryProduct, outletId);
    }

    private boolean isProductInStore(GroceryProduct groceryProduct, String storeId) {
        if (groceryProduct.getStore() == null) {
            return false;
        }
        return Objects.equals(groceryProduct.getStore().getOutletId(), storeId);
    }
}
