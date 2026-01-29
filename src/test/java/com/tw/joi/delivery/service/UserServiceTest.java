package com.tw.joi.delivery.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.tw.joi.delivery.domain.User;
import com.tw.joi.delivery.exception.NotFoundException;
import com.tw.joi.delivery.seed.SeedData;
import com.tw.joi.delivery.testutil.TestConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserServiceTest {

  private UserService userService;

  @BeforeEach
  void setUp() {
    SeedData.reset();
    userService = new UserService();
  }

  @Test
  void shouldReturnUserWhenExists() {
    User user = userService.fetchUserById(TestConstants.USER_ID_101);
    assertEquals(TestConstants.USER_ID_101, user.getUserId());
  }

  @Test
  void shouldThrowWhenUserDoesNotExist() {
    assertThrows(
        NotFoundException.class, () -> userService.fetchUserById(TestConstants.USER_ID_MISSING));
  }
}
