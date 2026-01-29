package com.tw.joi.delivery.dto.response;

import com.tw.joi.delivery.enums.ErrorCode;
import java.util.List;

/**
 * Standard error payload for API responses.
 *
 * @param code error code
 * @param message human-readable message
 * @param details error details
 */
public record ErrorResponse(ErrorCode code, String message, List<String> details) {}
