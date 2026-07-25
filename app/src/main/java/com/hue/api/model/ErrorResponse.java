package com.hue.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record ErrorResponse(
    @JsonProperty("error")
    String error,
    
    @JsonProperty("message")
    String message,
    
    @JsonProperty("status")
    int status,
    
    @JsonProperty("violations")
    List<String> violations
) {}
