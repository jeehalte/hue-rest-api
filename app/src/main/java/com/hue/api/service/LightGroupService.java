package com.hue.api.service;

import com.hue.api.model.ThemeRequest;
import com.hue.api.model.ThemeResponse;
import org.springframework.stereotype.Service;

@Service
public class LightGroupService {

  public ThemeResponse applyTheme(ThemeRequest request) {
    return new ThemeResponse(
        "Theme applied successfully to light group",
        request.theme(),
        request.brightness(),
        "applied"
    );
  }
}
