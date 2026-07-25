package com.hue.api.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record HueBridgeLightsResponse(
    @JsonProperty("data")
    List<HueBridgeLight> data
) {
  public List<HueBridgeLight> safeData() {
    return data == null ? List.of() : data;
  }
}
