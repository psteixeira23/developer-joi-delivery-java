package com.tw.joi.delivery.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.HashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/** Represents a grocery store outlet. */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GroceryStore extends Outlet {

  @JsonIgnore private Set<GroceryProduct> inventory = new HashSet<>();

  /**
   * Creates a grocery store instance.
   *
   * @param name store name
   * @param description store description
   * @param outletId store identifier
   */
  @Builder
  public GroceryStore(String name, String description, String outletId) {
    super(name, description, outletId);
    this.inventory = new HashSet<>();
  }
}
