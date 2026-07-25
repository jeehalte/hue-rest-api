package com.hue.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hue.api.model.ThemeRequest;
import com.hue.api.model.ThemeResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class LightGroupControllerTest {

  @Autowired
  private WebApplicationContext context;

  private MockMvc mockMvc;
  private ObjectMapper objectMapper;

  @BeforeEach
  void setup() {
    mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    objectMapper = new ObjectMapper();
  }

  @Test
  void testSuccessfulThemeApplication() throws Exception {
    ThemeRequest request = new ThemeRequest("cool", 75.0);

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

    mockMvc.perform(post("/light-groups/themes")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.brightness").value(0.0));
  }

  @Test
  void testBrightnessAtBoundaryHundred() throws Exception {
    ThemeRequest request = new ThemeRequest("cool", 100.0);

    mockMvc.perform(post("/light-groups/themes")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.brightness").value(100.0));
  }

  @Test
  void testDecimalBrightnessValue() throws Exception {
    ThemeRequest request = new ThemeRequest("warm", 75.5);

    mockMvc.perform(post("/light-groups/themes")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.brightness").value(75.5))
        .andExpect(jsonPath("$.theme").value("warm"));
  }
}

