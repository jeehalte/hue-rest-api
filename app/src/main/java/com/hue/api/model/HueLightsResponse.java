package com.hue.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record HueLightsResponse(
    @JsonProperty("lights")
    List<HueLightResponse> lights
) {
}
