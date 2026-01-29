package com.tw.joi.delivery.controller;

import com.tw.joi.delivery.domain.Cart;
import com.tw.joi.delivery.dto.request.AddProductRequest;
import com.tw.joi.delivery.dto.response.CartProductInfo;
import com.tw.joi.delivery.service.CartService;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.validation.annotation.Validated;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
@Validated
public class CartController {

    private final CartService cartService;

    @PostMapping("/product")
    public ResponseEntity<CartProductInfo> addProductToCart(@Valid @RequestBody AddProductRequest addProductRequest) {
        return ResponseEntity.ok(cartService.addProductToCartForUser(addProductRequest));
    }

    @GetMapping("/view")
    public ResponseEntity<Cart> viewCart(@RequestParam(name = "userId") @NotBlank String userId) {
        return ResponseEntity.ok(cartService.getCartForUser(userId));
    }
}
