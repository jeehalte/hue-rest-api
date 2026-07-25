package com.hue.api.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record HueBridgeDimming(
    @JsonProperty("brightness")
    Double brightness
) {
}
