package com.hue.api.client;

import com.hue.api.client.model.HueBridgeLightsResponse;
import com.hue.api.config.HueProperties;
import com.hue.api.exception.HueIntegrationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

@Component
public class RestClientHueRestClient implements HueRestClient {

  private static final Logger log = LoggerFactory.getLogger(RestClientHueRestClient.class);

  private final RestClient restClient;
  private final HueProperties hueProperties;

  public RestClientHueRestClient(RestClient restClient, HueProperties hueProperties) {
    this.restClient = restClient;
    this.hueProperties = hueProperties;
  }

  @Override
  public HueBridgeLightsResponse fetchLights() {
    validateConfiguration();

    try {
      HueBridgeLightsResponse response = restClient.get()
          .uri(buildLightsUri())
          .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
          .header("hue-application-key", hueProperties.applicationKey())
          .retrieve()
          .body(HueBridgeLightsResponse.class);

      if (response == null) {
        log.error("Hue bridge returned an empty response for lights endpoint");
        throw new HueIntegrationException("Hue bridge returned an empty response");
      }
      return response;
    } catch (RestClientException ex) {
      log.error("Hue bridge lights request failed", ex);
      throw new HueIntegrationException("Failed to retrieve lights from Hue bridge", ex);
    }
  }

  private String buildLightsUri() {
    return normalizeBaseUrl(hueProperties.bridgeBaseUrl()) + "/clip/v2/resource/light";
  }

  private String normalizeBaseUrl(String baseUrl) {
    return baseUrl.endsWith("/") ? baseUrl.substring(0, baseUrl.length() - 1) : baseUrl;
  }

  private void validateConfiguration() {
    if (!StringUtils.hasText(hueProperties.bridgeBaseUrl())) {
      log.error("Hue bridge base URL is missing");
      throw new HueIntegrationException("Hue bridge base URL is not configured");
    }
    if (!StringUtils.hasText(hueProperties.applicationKey())) {
      log.error("Hue application key is missing");
      throw new HueIntegrationException("Hue application key is not configured");
    }
  }
}
