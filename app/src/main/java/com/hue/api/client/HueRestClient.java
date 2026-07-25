package com.hue.api.client;

import com.hue.api.client.model.HueBridgeLightsResponse;

public interface HueRestClient {

  HueBridgeLightsResponse fetchLights();
}
