package com.hue.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hue.api.model.ThemeRequest;
import com.hue.api.model.ThemeResponse;
import com.hue.api.service.LightGroupService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LightGroupController.class)
public class LightGroupControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @MockBean
  private LightGroupService lightGroupService;

  @Test
  void testSuccessfulThemeApplication() throws Exception {
    ThemeRequest request = new ThemeRequest("cool", 75.0);
    ThemeResponse response = new ThemeResponse("Theme applied successfully to light group", "cool", 75.0, "applied");
    
    when(lightGroupService.applyTheme(any(ThemeRequest.class))).thenReturn(response);

    mockMvc.perform(post("/light-groups/themes")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.status").value("applied"))
        .andExpect(jsonPath("$.brightness").value(75.0))
        .andExpect(jsonPath("$.theme").value("cool"));
  }

  @Test
  void testMissingThemeFieldValidation() throws Exception {
    String request = "{\"brightness\": 75.0}";

    mockMvc.perform(post("/light-groups/themes")
        .contentType(MediaType.APPLICATION_JSON)
        .content(request))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.status").value(400));
  }

  @Test
  void testInvalidBrightnessAboveMaximum() throws Exception {
    ThemeRequest request = new ThemeRequest("cool", 101.0);

    mockMvc.perform(post("/light-groups/themes")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.status").value(400));
  }

  @Test
  void testInvalidBrightnessBelowMinimum() throws Exception {
    ThemeRequest request = new ThemeRequest("cool", -1.0);

    mockMvc.perform(post("/light-groups/themes")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.status").value(400));
  }

  @Test
  void testBrightnessAtBoundaryZero() throws Exception {
    ThemeRequest request = new ThemeRequest("cool", 0.0);
    ThemeResponse response = new ThemeResponse("Theme applied successfully to light group", "cool", 0.0, "applied");
    
    when(lightGroupService.applyTheme(any(ThemeRequest.class))).thenReturn(response);

    mockMvc.perform(post("/light-groups/themes")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.brightness").value(0.0));
  }

  @Test
  void testBrightnessAtBoundaryHundred() throws Exception {
    ThemeRequest request = new ThemeRequest("cool", 100.0);
    ThemeResponse response = new ThemeResponse("Theme applied successfully to light group", "cool", 100.0, "applied");
    
    when(lightGroupService.applyTheme(any(ThemeRequest.class))).thenReturn(response);

    mockMvc.perform(post("/light-groups/themes")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.brightness").value(100.0));
  }

  @Test
  void testDecimalBrightnessValue() throws Exception {
    ThemeRequest request = new ThemeRequest("warm", 75.5);
    ThemeResponse response = new ThemeResponse("Theme applied successfully to light group", "warm", 75.5, "applied");
    
    when(lightGroupService.applyTheme(any(ThemeRequest.class))).thenReturn(response);

    mockMvc.perform(post("/light-groups/themes")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.brightness").value(75.5))
        .andExpect(jsonPath("$.theme").value("warm"));
  }
}

