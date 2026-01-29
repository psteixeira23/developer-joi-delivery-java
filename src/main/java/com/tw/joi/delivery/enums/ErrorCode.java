package com.tw.joi.delivery.enums;

public enum ErrorCode {
    NOT_FOUND("Resource not found"),
    VALIDATION_ERROR("Invalid request"),
    BAD_REQUEST("Bad request");

    private final String defaultMessage;

    ErrorCode(String defaultMessage) {
        this.defaultMessage = defaultMessage;
    }

    public String defaultMessage() {
        return defaultMessage;
    }
}
