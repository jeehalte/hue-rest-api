package com.hue.api.controller;

import com.hue.api.model.HueLightsResponse;
import com.hue.api.service.HueService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/lights")
public class HueLightsController {

  private final HueService hueService;

  public HueLightsController(HueService hueService) {
    this.hueService = hueService;
  }

  @GetMapping
  public ResponseEntity<HueLightsResponse> getLights() {
    return ResponseEntity.status(HttpStatus.OK).body(hueService.getLights());
  }
}
