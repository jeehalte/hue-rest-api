package com.hue.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record HueLightColorResponse(
    @JsonProperty("x")
    Double x,

    @JsonProperty("y")
    Double y
) {
}
