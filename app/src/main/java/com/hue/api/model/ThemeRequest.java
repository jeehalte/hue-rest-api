package com.hue.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ThemeRequest {

  @NotBlank(message = "Theme name is required")
  @JsonProperty("theme")
  private String theme;

  @DecimalMin(value = "0.0", message = "Brightness must be at least 0")
  @DecimalMax(value = "100.0", message = "Brightness must not exceed 100")
  @JsonProperty("brightness")
  private Double brightness;
}
