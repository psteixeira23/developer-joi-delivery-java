package com.tw.joi.delivery.dto.response;

import com.tw.joi.delivery.enums.ErrorCode;
import java.util.List;

public record ErrorResponse(ErrorCode code, String message, List<String> details) {
}
