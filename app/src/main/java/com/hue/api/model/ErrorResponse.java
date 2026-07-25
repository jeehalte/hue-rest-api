package com.hue.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {

  @JsonProperty("error")
  private String error;

  @JsonProperty("message")
  private String message;

  @JsonProperty("status")
  private int status;

  @JsonProperty("violations")
  private List<String> violations;

  public ErrorResponse(String error, String message, int status) {
    this.error = error;
    this.message = message;
    this.status = status;
  }
}
