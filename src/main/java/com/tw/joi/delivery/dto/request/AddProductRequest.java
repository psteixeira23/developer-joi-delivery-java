package com.tw.joi.delivery.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/** Request payload to add a product to a cart. */
@Getter
@Setter
public class AddProductRequest {

  @NotBlank(message = "userId is required")
  private String userId;

  @NotBlank(message = "outletId is required")
  private String outletId;

  @NotBlank(message = "productId is required")
  private String productId;
}
