package com.hue.api.controller;

import com.hue.api.model.ThemeRequest;
import com.hue.api.model.ThemeResponse;
import com.hue.api.service.LightGroupService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/light-groups")
public class LightGroupController {

  @Autowired
  private LightGroupService lightGroupService;

  @PostMapping("/themes")
  public ResponseEntity<ThemeResponse> applyTheme(@Valid @RequestBody ThemeRequest request) {
    ThemeResponse response = lightGroupService.applyTheme(request);
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }
}
