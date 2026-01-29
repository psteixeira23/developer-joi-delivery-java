package com.tw.joi.delivery.controller;

import com.tw.joi.delivery.domain.Cart;
import com.tw.joi.delivery.dto.request.AddProductRequest;
import com.tw.joi.delivery.dto.response.CartProductInfo;
import com.tw.joi.delivery.service.CartService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/** REST endpoints for cart operations. */
@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
@Validated
public class CartController {

  private final CartService cartService;

  /**
   * Adds a product to a user's cart.
   *
   * @param addProductRequest request payload
   * @return cart and product details
   */
  @PostMapping("/product")
  public ResponseEntity<CartProductInfo> addProductToCart(
      @Valid @RequestBody AddProductRequest addProductRequest) {
    return ResponseEntity.ok(cartService.addProductToCartForUser(addProductRequest));
  }

  /**
   * Returns the cart for a given user.
   *
   * @param userId user identifier
   * @return cart details
   */
  @GetMapping("/view")
  public ResponseEntity<Cart> viewCart(@RequestParam(name = "userId") @NotBlank String userId) {
    return ResponseEntity.ok(cartService.getCartForUser(userId));
  }
}
