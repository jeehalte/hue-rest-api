package com.hue.api.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestClient;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class RestClientConfigTest {

  @Autowired
  private RestClient restClient;

  @Test
  void testRestClientBeanIsProvided() {
    assertThat(restClient).isNotNull();
  }

  @Test
  void testRestClientCanBeInjected() {
    assertThat(restClient).isInstanceOf(RestClient.class);
  }
}
