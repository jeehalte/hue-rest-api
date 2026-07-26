package com.hue.api.config;

import javax.net.ssl.SSLContext;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManagerBuilder;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.ssl.NoopHostnameVerifier;
import org.apache.hc.client5.http.ssl.SSLConnectionSocketFactory;
import org.apache.hc.client5.http.ssl.SSLConnectionSocketFactoryBuilder;
import org.apache.hc.core5.ssl.SSLContexts;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {

  @Bean
  @Primary
  public RestClient restClient() {
    return RestClient.builder()
        .build();
  }

  @Bean("hueRestClient")
  public RestClient hueRestClient() {
    SSLContext sslContext = SSLContexts.createSystemDefault();
    SSLConnectionSocketFactory socketFactory = SSLConnectionSocketFactoryBuilder.create()
        .setSslContext(sslContext)
        .setHostnameVerifier(NoopHostnameVerifier.INSTANCE)
        .build();
    PoolingHttpClientConnectionManager connectionManager =
        PoolingHttpClientConnectionManagerBuilder.create()
            .setSSLSocketFactory(socketFactory)
            .build();
    CloseableHttpClient httpClient = HttpClients.custom()
        .setConnectionManager(connectionManager)
        .build();

    return RestClient.builder()
        .requestFactory(new HttpComponentsClientHttpRequestFactory(httpClient))
        .build();
  }
}
