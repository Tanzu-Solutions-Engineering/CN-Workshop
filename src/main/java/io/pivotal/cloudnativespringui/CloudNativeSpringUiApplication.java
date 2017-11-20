package io.pivotal.cloudnativespringui;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.hateoas.Resources;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import io.pivotal.cloudnativespring.domain.City;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
@EnableCircuitBreaker
public class CloudNativeSpringUiApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudNativeSpringUiApplication.class, args);
    }

    @FeignClient(name = "cloud-native-spring", fallback = CityClientFallback.class)
    public interface CityClient {
        @GetMapping(value = "/cities", consumes = "application/hal+json")
        Resources<City> getCities();
    }

    @Component
    public class CityClientFallback implements CityClient {
        @Override
        public Resources<City> getCities() {
            // We'll just return an empty response
            return new Resources<City>(Collections.emptyList());
        }
    }
}