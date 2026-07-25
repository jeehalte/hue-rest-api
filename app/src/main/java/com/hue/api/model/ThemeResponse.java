package com.hue.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ThemeResponse(
    @JsonProperty("message")
    String message,
    
    @JsonProperty("theme")
    String theme,
    
    @JsonProperty("brightness")
    Double brightness,
    
    @JsonProperty("status")
    String status
) {}
