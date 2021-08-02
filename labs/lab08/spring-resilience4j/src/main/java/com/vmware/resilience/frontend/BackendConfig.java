package com.vmware.resilience.frontend;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Profile("frontend")
@Configuration
public class BackendConfig
{
    @Bean
    public RestTemplate restTemplate(
            RestTemplateBuilder restTemplateBuilder,
            @Value("${backend.baseUrl}") String baseUrl,
            @Value("${backend.connectionTimeout}") Duration connectionTimeout,
            @Value("${backend.readTimeout}") Duration readTimeout)
    {
        return restTemplateBuilder
           .rootUri(baseUrl)
           .setConnectTimeout(connectionTimeout)
           .setReadTimeout(readTimeout)
           .build();
    }
}