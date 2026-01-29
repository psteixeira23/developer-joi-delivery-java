package com.tw.joi.delivery.enums;

/** Error codes used in API responses. */
public enum ErrorCode {
  NOT_FOUND("Resource not found"),
  VALIDATION_ERROR("Invalid request"),
  BAD_REQUEST("Bad request");

  private final String defaultMessage;

  ErrorCode(String defaultMessage) {
    this.defaultMessage = defaultMessage;
  }

  /**
   * Returns the default message for this code.
   *
   * @return default message
   */
  public String defaultMessage() {
    return defaultMessage;
  }
}
