package com.tw.joi.delivery.seed;

import com.tw.joi.delivery.domain.Cart;
import com.tw.joi.delivery.domain.GroceryProduct;
import com.tw.joi.delivery.domain.GroceryStore;
import com.tw.joi.delivery.domain.User;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** Provides deterministic seed data for development and tests. */
public final class SeedData {

  private static GroceryStore store101;
  private static GroceryStore store102;
  private static User user101;
  private static User user102;

  private static final List<GroceryStore> stores = new ArrayList<>();
  private static final List<GroceryProduct> groceryProducts = new ArrayList<>();
  private static final List<User> users = new ArrayList<>();
  private static final Map<String, Cart> cartForUsers = new HashMap<>();

  private SeedData() {}

  static {
    reset();
  }

  /** Resets all seed data to the default state. */
  public static void reset() {
    stores.clear();
    groceryProducts.clear();
    users.clear();
    cartForUsers.clear();

    store101 = SeedData.createStore("Fresh Picks", "store101");
    store102 = SeedData.createStore("Natural Choice", "store102");
    user101 = SeedData.createUser("user101", "John", "Doe");
    user102 = SeedData.createUser("user102", "Rachel", "Zane");

    groceryProducts.add(createGroceryProduct("Wheat Bread", "product101", store101));
    groceryProducts.add(createGroceryProduct("Spinach", "product102", store101));
    groceryProducts.add(createGroceryProduct("Crackers", "product103", store101));

    store101.getInventory().addAll(groceryProducts);

    stores.add(store101);
    stores.add(store102);
    users.add(user101);
    users.add(user102);

    cartForUsers.put(user101.getUserId(), createCartForUser(user101, store101, "cart101"));
    cartForUsers.put(user102.getUserId(), createCartForUser(user102, store102, "cart102"));
  }

  /**
   * Creates a cart for a user in a given store.
   *
   * @param user user reference
   * @param store store reference
   * @param cartId cart identifier
   * @return created cart
   */
  public static Cart createCartForUser(User user, GroceryStore store, String cartId) {
    Cart cart = Cart.builder().cartId(cartId).outlet(store).user(user).build();
    user.setCart(cart);
    return cart;
  }

  /**
   * Creates a grocery store.
   *
   * @param outletName store name
   * @param storeId store identifier
   * @return grocery store
   */
  public static GroceryStore createStore(String outletName, String storeId) {
    return GroceryStore.builder().name(outletName).outletId(storeId).build();
  }

  /**
   * Creates a user with deterministic phone number.
   *
   * @param userId user identifier
   * @param firstName first name
   * @param lastName last name
   * @return user
   */
  public static User createUser(String userId, String firstName, String lastName) {
    return User.builder()
        .userId(userId)
        .firstName(firstName)
        .lastName(lastName)
        .email(firstName + "." + lastName + "@gmail.com")
        .phoneNumber(generatePhoneNumber(userId))
        .build();
  }

  private static String generatePhoneNumber(String userId) {
    int suffix = Math.abs(userId.hashCode() % 900000000);
    return String.valueOf(100000000 + suffix);
  }

  private static GroceryProduct createGroceryProduct(
      String productName, String productId, GroceryStore store) {
    return GroceryProduct.builder()
        .productName(productName)
        .productId(productId)
        .mrp(BigDecimal.valueOf(10.5))
        .weight(BigDecimal.valueOf(500.00))
        .store(store)
        .threshold(10)
        .availableStock(30)
        .build();
  }

  /**
   * Returns the default user (user101).
   *
   * @return user101
   */
  public static User getUser101() {
    return user101;
  }

  /**
   * Returns an unmodifiable list of stores.
   *
   * @return stores
   */
  public static List<GroceryStore> getStores() {
    return Collections.unmodifiableList(stores);
  }

  /**
   * Returns an unmodifiable list of grocery products.
   *
   * @return grocery products
   */
  public static List<GroceryProduct> getGroceryProducts() {
    return Collections.unmodifiableList(groceryProducts);
  }

  /**
   * Returns an unmodifiable list of users.
   *
   * @return users
   */
  public static List<User> getUsers() {
    return Collections.unmodifiableList(users);
  }

  /**
   * Returns an unmodifiable map of carts keyed by userId.
   *
   * @return carts by userId
   */
  public static Map<String, Cart> getCartForUsers() {
    return Collections.unmodifiableMap(cartForUsers);
  }

  /**
   * Removes a cart for a given userId (test helper).
   *
   * @param userId user identifier
   */
  public static void removeCartForUser(String userId) {
    cartForUsers.remove(userId);
  }
}
