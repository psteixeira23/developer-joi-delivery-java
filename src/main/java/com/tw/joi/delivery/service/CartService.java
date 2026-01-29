package com.tw.joi.delivery.service;

import com.tw.joi.delivery.domain.Cart;
import com.tw.joi.delivery.domain.GroceryProduct;
import com.tw.joi.delivery.domain.User;
import com.tw.joi.delivery.dto.request.AddProductRequest;
import com.tw.joi.delivery.dto.response.CartProductInfo;
import com.tw.joi.delivery.exception.NotFoundException;
import com.tw.joi.delivery.seed.SeedData;
import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/** Service for cart operations. */
@Service
@RequiredArgsConstructor
public class CartService {

  private final Map<String, Cart> userCarts = SeedData.getCartForUsers();
  private final UserService userService;
  private final ProductService productService;

  /**
   * Adds a product to a user's cart.
   *
   * @param addProductRequest request payload
   * @return cart and product details
   */
  public CartProductInfo addProductToCartForUser(AddProductRequest addProductRequest) {
    User user = userService.fetchUserById(addProductRequest.getUserId());
    Cart cart = fetchCartForUser(user.getUserId());
    GroceryProduct product =
        productService.getProduct(
            addProductRequest.getProductId(), addProductRequest.getOutletId());
    addProduct(cart, product);
    return new CartProductInfo(cart, product, product.getSellingPrice());
  }

  /**
   * Returns the cart for a given userId.
   *
   * @param userId user identifier
   * @return cart
   */
  public Cart getCartForUser(String userId) {
    User user = userService.fetchUserById(userId);
    return fetchCartForUser(user.getUserId());
  }

  private Cart fetchCartForUser(String userId) {
    return fetchCartForUserOptional(userId)
        .orElseThrow(() -> new NotFoundException("Cart not found for userId=" + userId));
  }

  private Optional<Cart> fetchCartForUserOptional(String userId) {
    if (userId == null) {
      throw new IllegalArgumentException("userId is required");
    }
    return Optional.ofNullable(userCarts.get(userId));
  }

  private void addProduct(Cart cart, GroceryProduct product) {
    cart.addProduct(product);
  }
}
