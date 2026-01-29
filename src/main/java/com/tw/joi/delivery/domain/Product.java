package com.tw.joi.delivery.domain;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** Base class for products in the catalog. */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class Product {

  protected String productId;
  protected String productName;
  protected BigDecimal mrp;
}
