package com.hue.api.service;

import com.hue.api.client.HueRestClient;
import com.hue.api.client.model.HueBridgeColor;
import com.hue.api.client.model.HueBridgeDimming;
import com.hue.api.client.model.HueBridgeLight;
import com.hue.api.client.model.HueBridgeMetadata;
import com.hue.api.client.model.HueBridgeOn;
import com.hue.api.client.model.HueBridgeXy;
import com.hue.api.model.HueLightColorResponse;
import com.hue.api.model.HueLightResponse;
import com.hue.api.model.HueLightsResponse;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class HueService {

  private final HueRestClient hueRestClient;

  public HueService(HueRestClient hueRestClient) {
    this.hueRestClient = hueRestClient;
  }

  public HueLightsResponse getLights() {
    List<HueLightResponse> lights = hueRestClient.fetchLights()
        .safeData()
        .stream()
        .map(this::mapLight)
        .toList();
    return new HueLightsResponse(lights);
  }

  private HueLightResponse mapLight(HueBridgeLight light) {
    return new HueLightResponse(
        light.id(),
        metadataName(light.metadata()),
        light.type(),
        isOn(light.on()),
        brightness(light.dimming()),
        mapColor(light.color())
    );
  }

  private String metadataName(HueBridgeMetadata metadata) {
    return metadata == null ? null : metadata.name();
  }

  private Boolean isOn(HueBridgeOn on) {
    return on == null ? null : on.on();
  }

  private Double brightness(HueBridgeDimming dimming) {
    return dimming == null ? null : dimming.brightness();
  }

  private HueLightColorResponse mapColor(HueBridgeColor color) {
    if (color == null || color.xy() == null) {
      return null;
    }
    HueBridgeXy xy = color.xy();
    return new HueLightColorResponse(xy.x(), xy.y());
  }
}
