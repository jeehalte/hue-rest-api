package com.hue.api.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hue.api.client.HueRestClient;
import com.hue.api.client.model.HueBridgeLightsResponse;
import com.hue.api.model.HueLightsResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class HueServiceTest {

  @Test
  void mapsHueBridgeFixtureToApiOwnedModel() throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    String fixture = Files.readString(Path.of("src/test/resources/hue/lights-response.json"));
    HueBridgeLightsResponse bridgeResponse = objectMapper.readValue(fixture, HueBridgeLightsResponse.class);

    HueRestClient hueRestClient = mock(HueRestClient.class);
    when(hueRestClient.fetchLights()).thenReturn(bridgeResponse);

    HueService hueService = new HueService(hueRestClient);
    HueLightsResponse result = hueService.getLights();

    assertThat(result.lights()).hasSize(2);
    assertThat(result.lights().get(0).id()).isEqualTo("13e52066-9953-42d4-8bac-cd9f3adc14b0");
    assertThat(result.lights().get(0).name()).isEqualTo("Hue play gradient lightstrip");
    assertThat(result.lights().get(0).isOn()).isFalse();
    assertThat(result.lights().get(0).brightness()).isEqualTo(25.3);
    assertThat(result.lights().get(0).color().x()).isEqualTo(0.6443);
    assertThat(result.lights().get(0).color().y()).isEqualTo(0.3206);
  }
}
