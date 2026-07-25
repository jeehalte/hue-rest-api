package com.hue.api.controller;

import com.hue.api.model.ErrorResponse;
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
}
