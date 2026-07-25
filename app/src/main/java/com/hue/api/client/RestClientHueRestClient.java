package com.hue.api.client;

import com.hue.api.client.model.HueBridgeLightsResponse;
import com.hue.api.config.HueProperties;
import com.hue.api.exception.HueIntegrationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

@Component
public class RestClientHueRestClient implements HueRestClient {

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
        throw new HueIntegrationException("Hue bridge returned an empty response");
      }
      return response;
    } catch (RestClientException ex) {
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
      throw new HueIntegrationException("Hue bridge base URL is not configured");
    }
    if (!StringUtils.hasText(hueProperties.applicationKey())) {
      throw new HueIntegrationException("Hue application key is not configured");
    }
  }
}
