package com.tw.joi.delivery.controller;

import com.tw.joi.delivery.dto.response.InventoryHealthResponse;
import com.tw.joi.delivery.service.InventoryService;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.validation.annotation.Validated;

@RestController
@RequestMapping("/inventory")
@RequiredArgsConstructor
@Validated
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping("/health")
    public ResponseEntity<InventoryHealthResponse> fetchStoreInventoryHealth(
        @RequestParam(name = "storeId") @NotBlank String storeId) {
        return ResponseEntity.ok(inventoryService.fetchStoreInventoryHealth(storeId));
    }
}
