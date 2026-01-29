package com.tw.joi.delivery.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** Represents a user in the system. */
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

  private String userId;
  private String username;
  private String firstName;
  private String lastName;
  private String email;
  private String phoneNumber;

  @JsonIgnore private Cart cart;
}
