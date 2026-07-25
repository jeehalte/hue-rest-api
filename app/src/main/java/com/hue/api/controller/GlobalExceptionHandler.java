package com.hue.api.controller;

import com.hue.api.model.ErrorResponse;
import com.hue.api.exception.HueIntegrationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException ex) {
    List<String> violations = new ArrayList<>();
    ex.getBindingResult().getFieldErrors().forEach(error ->
        violations.add(error.getField() + ": " + error.getDefaultMessage())
    );

    ErrorResponse errorResponse = new ErrorResponse(
        "Validation Failed",
        "Request validation failed",
        HttpStatus.BAD_REQUEST.value(),
        violations
    );

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
  }

  @ExceptionHandler(HueIntegrationException.class)
  public ResponseEntity<ErrorResponse> handleHueIntegrationException(HueIntegrationException ex) {
    ErrorResponse errorResponse = new ErrorResponse(
        "Hue Integration Failed",
        "Unable to retrieve lights from Hue bridge",
        HttpStatus.BAD_GATEWAY.value(),
        List.of()
    );
    return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(errorResponse);
  }
}
