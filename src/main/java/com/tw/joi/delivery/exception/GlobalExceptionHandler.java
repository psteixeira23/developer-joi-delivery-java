package com.tw.joi.delivery.exception;

import com.tw.joi.delivery.dto.response.ErrorResponse;
import com.tw.joi.delivery.enums.ErrorCode;
import jakarta.validation.ConstraintViolationException;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/** Maps exceptions to standardized API error responses. */
@RestControllerAdvice
public class GlobalExceptionHandler {

  /**
   * Handles resource not found errors.
   *
   * @param ex exception
   * @return error response
   */
  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<ErrorResponse> handleNotFound(NotFoundException ex) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body(new ErrorResponse(ErrorCode.NOT_FOUND, ex.getMessage(), List.of()));
  }

  /**
   * Handles request validation errors.
   *
   * @param ex exception
   * @return error response
   */
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex) {
    List<String> details =
        ex.getBindingResult().getFieldErrors().stream()
            .map(GlobalExceptionHandler::formatFieldError)
            .toList();
    return ResponseEntity.badRequest()
        .body(
            new ErrorResponse(
                ErrorCode.VALIDATION_ERROR, ErrorCode.VALIDATION_ERROR.defaultMessage(), details));
  }

  /**
   * Handles invalid argument errors.
   *
   * @param ex exception
   * @return error response
   */
  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<ErrorResponse> handleIllegalArgument(IllegalArgumentException ex) {
    return ResponseEntity.badRequest()
        .body(new ErrorResponse(ErrorCode.BAD_REQUEST, ex.getMessage(), List.of()));
  }

  /**
   * Handles constraint validation errors.
   *
   * @param ex exception
   * @return error response
   */
  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<ErrorResponse> handleConstraintViolation(ConstraintViolationException ex) {
    List<String> details =
        ex.getConstraintViolations().stream()
            .map(violation -> violation.getPropertyPath() + ": " + violation.getMessage())
            .toList();
    return ResponseEntity.badRequest()
        .body(
            new ErrorResponse(
                ErrorCode.VALIDATION_ERROR, ErrorCode.VALIDATION_ERROR.defaultMessage(), details));
  }

  private static String formatFieldError(FieldError error) {
    return error.getField() + ": " + error.getDefaultMessage();
  }
}
