package com.hue.api.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record HueBridgeLight(
    @JsonProperty("id")
    String id,

    @JsonProperty("type")
    String type,

    @JsonProperty("metadata")
    HueBridgeMetadata metadata,

    @JsonProperty("on")
    HueBridgeOn on,

    @JsonProperty("dimming")
    HueBridgeDimming dimming,

    @JsonProperty("color")
    HueBridgeColor color
) {
}
