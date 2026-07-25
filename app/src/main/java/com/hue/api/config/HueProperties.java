package com.hue.api.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "hue")
public record HueProperties(
    String bridgeBaseUrl,
    String applicationKey
) {
}
