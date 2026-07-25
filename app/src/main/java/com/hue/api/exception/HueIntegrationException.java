package com.hue.api.exception;

public class HueIntegrationException extends RuntimeException {

  public HueIntegrationException(String message) {
    super(message);
  }

  public HueIntegrationException(String message, Throwable cause) {
    super(message, cause);
  }
}
