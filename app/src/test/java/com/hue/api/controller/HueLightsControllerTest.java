package com.hue.api.controller;

import com.hue.api.exception.HueIntegrationException;
import com.hue.api.model.HueLightColorResponse;
import com.hue.api.model.HueLightResponse;
import com.hue.api.model.HueLightsResponse;
import com.hue.api.service.HueService;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class HueLightsControllerTest {

  private MockMvc mockMvc;
  private HueService hueService;

  @BeforeEach
  void setUp() {
    hueService = mock(HueService.class);
    HueLightsController controller = new HueLightsController(hueService);
    mockMvc = MockMvcBuilders.standaloneSetup(controller)
        .setControllerAdvice(new GlobalExceptionHandler())
        .build();
  }

  @Test
  void getLightsReturnsMappedResponseShape() throws Exception {
    HueLightsResponse response = new HueLightsResponse(List.of(
        new HueLightResponse(
            "13e52066-9953-42d4-8bac-cd9f3adc14b0",
            "Hue play gradient lightstrip",
            "light",
            false,
            25.3,
            new HueLightColorResponse(0.6443, 0.3206)
        )
    ));
    when(hueService.getLights()).thenReturn(response);

    mockMvc.perform(get("/v1/lights").accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.lights[0].id").value("13e52066-9953-42d4-8bac-cd9f3adc14b0"))
        .andExpect(jsonPath("$.lights[0].name").value("Hue play gradient lightstrip"))
        .andExpect(jsonPath("$.lights[0].type").value("light"))
        .andExpect(jsonPath("$.lights[0].isOn").value(false))
        .andExpect(jsonPath("$.lights[0].brightness").value(25.3))
        .andExpect(jsonPath("$.lights[0].color.x").value(0.6443))
        .andExpect(jsonPath("$.lights[0].color.y").value(0.3206));
  }

  @Test
  void getLightsMapsIntegrationFailureToSafeError() throws Exception {
    when(hueService.getLights()).thenThrow(new HueIntegrationException("upstream details"));

    mockMvc.perform(get("/v1/lights").accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadGateway())
        .andExpect(jsonPath("$.error").value("Hue Integration Failed"))
        .andExpect(jsonPath("$.message").value("Unable to retrieve lights from Hue bridge"))
        .andExpect(jsonPath("$.status").value(502));
  }

  @Test
  void getLightsDoesNotExposeRawClientFields() throws Exception {
    HueLightsResponse response = new HueLightsResponse(List.of(
        new HueLightResponse("abc", "Kitchen", "light", true, 88.5, new HueLightColorResponse(0.5, 0.4))
    ));
    when(hueService.getLights()).thenReturn(response);

    mockMvc.perform(get("/v1/lights").accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.lights[0].metadata").doesNotExist())
        .andExpect(jsonPath("$.lights[0].owner").doesNotExist())
        .andExpect(jsonPath("$.lights[0].id_v1").doesNotExist())
        .andExpect(jsonPath("$.lights[0].product_data").doesNotExist());
  }
}
