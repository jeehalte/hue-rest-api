package com.hue.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class HueRestApiApplication {

  public static void main(String[] args) {
    SpringApplication.run(HueRestApiApplication.class, args);
  }
}
