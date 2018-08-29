package io.pivotal.cloudnativespringui;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.Resources;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
@EnableFeignClients // <---- Add this
@EnableDiscoveryClient // <---- And this
@EnableCircuitBreaker
@EnableAutoConfiguration(exclude = { SecurityAutoConfiguration.class }) // To Bypass Security in the Demo Application

public class CloudNativeSpringUiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudNativeSpringUiApplication.class, args);
	}

	// Lab06 - @FeignClient(name = "cloud-native-spring-lab05", fallback = CityClientFallback.class)
	@FeignClient("cloud-native-spring-lab05")
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
