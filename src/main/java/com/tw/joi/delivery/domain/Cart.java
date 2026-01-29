package com.tw.joi.delivery.domain;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** Represents a user's cart. */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Cart {

  private String cartId;
  private Outlet outlet;

  @Builder.Default private List<Product> products = new ArrayList<>();

  private User user;

  /**
   * Adds a product to the cart.
   *
   * @param product product to add
   */
  public void addProduct(Product product) {
    products.add(product);
  }
}
