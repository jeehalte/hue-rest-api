package com.hue.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record HueLightResponse(
    @JsonProperty("id")
    String id,

    @JsonProperty("name")
    String name,

    @JsonProperty("type")
    String type,

    @JsonProperty("isOn")
    Boolean isOn,

    @JsonProperty("brightness")
    Double brightness,

    @JsonProperty("color")
    HueLightColorResponse color
) {
}
