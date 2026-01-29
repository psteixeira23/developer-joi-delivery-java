package com.tw.joi.delivery.controller;

import com.tw.joi.delivery.dto.response.InventoryHealthResponse;
import com.tw.joi.delivery.service.InventoryService;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/** REST endpoints for inventory health. */
@RestController
@RequestMapping("/inventory")
@RequiredArgsConstructor
@Validated
public class InventoryController {

  private final InventoryService inventoryService;

  /**
   * Returns the inventory health for a store.
   *
   * @param storeId store identifier
   * @return inventory health details
   */
  @GetMapping("/health")
  public ResponseEntity<InventoryHealthResponse> fetchStoreInventoryHealth(
      @RequestParam(name = "storeId") @NotBlank String storeId) {
    return ResponseEntity.ok(inventoryService.fetchStoreInventoryHealth(storeId));
  }
}
