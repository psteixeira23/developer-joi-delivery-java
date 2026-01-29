package com.tw.joi.delivery.service;

import com.tw.joi.delivery.domain.User;
import com.tw.joi.delivery.exception.NotFoundException;
import com.tw.joi.delivery.seed.SeedData;
import java.util.List;
import java.util.Objects;
import org.springframework.stereotype.Service;

/** Service for user queries. */
@Service
public class UserService {

  private final List<User> users = SeedData.getUsers();

  /**
   * Returns a user by userId.
   *
   * @param userId user identifier
   * @return user
   */
  public User fetchUserById(String userId) {
    return users.stream()
        .filter(user -> Objects.equals(userId, user.getUserId()))
        .findFirst()
        .orElseThrow(() -> new NotFoundException("User not found for userId=" + userId));
  }
}
