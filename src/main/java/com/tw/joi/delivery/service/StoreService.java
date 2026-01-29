package com.tw.joi.delivery.service;

import com.tw.joi.delivery.domain.GroceryStore;
import com.tw.joi.delivery.exception.NotFoundException;
import com.tw.joi.delivery.seed.SeedData;
import java.util.List;
import java.util.Objects;
import org.springframework.stereotype.Service;

@Service
public class StoreService {

    private final List<GroceryStore> stores = SeedData.getStores();

    public GroceryStore fetchStoreById(String storeId) {
        return stores.stream()
            .filter(store -> Objects.equals(storeId, store.getOutletId()))
            .findFirst()
            .orElseThrow(() -> new NotFoundException("Store not found for storeId=" + storeId));
    }
}
