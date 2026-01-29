package com.tw.joi.delivery.domain;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** Represents a grocery product with inventory and pricing details. */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GroceryProduct extends Product {

  private BigDecimal sellingPrice;
  private BigDecimal weight;

  private int expiryDate;

  private int threshold;

  private int availableStock;

  private BigDecimal discount;

  private GroceryStore store;

  /**
   * Creates a grocery product instance.
   *
   * @param productId product identifier
   * @param productName product name
   * @param mrp maximum retail price
   * @param sellingPrice current selling price
   * @param weight product weight
   * @param expiryDate expiry date (raw value)
   * @param threshold low-stock threshold
   * @param availableStock available stock count
   * @param store store reference
   * @param discount discount value
   */
  @Builder
  public GroceryProduct(
      String productId,
      String productName,
      BigDecimal mrp,
      BigDecimal sellingPrice,
      BigDecimal weight,
      int expiryDate,
      int threshold,
      int availableStock,
      GroceryStore store,
      BigDecimal discount) {
    super(productId, productName, mrp);
    this.sellingPrice = sellingPrice;
    this.weight = weight;
    this.expiryDate = expiryDate;
    this.threshold = threshold;
    this.availableStock = availableStock;
    this.store = store;
    this.discount = discount;
  }
}
