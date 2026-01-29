package com.tw.joi.delivery.exception;

/** Exception for missing resources. */
public class NotFoundException extends RuntimeException {

  /**
   * Creates a new NotFoundException with a message.
   *
   * @param message error message
   */
  public NotFoundException(String message) {
    super(message);
  }
}
