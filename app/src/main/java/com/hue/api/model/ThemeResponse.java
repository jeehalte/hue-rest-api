package com.hue.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ThemeResponse {

  @JsonProperty("message")
  private String message;

  @JsonProperty("theme")
  private String theme;

  @JsonProperty("brightness")
  private Double brightness;

  @JsonProperty("status")
  private String status;
}
