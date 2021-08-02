package com.vmware.resilience.frontend;

import com.vmware.resilience.backend.BackendResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.Optional;

@Profile("frontend")
@Service
public class BackendService {

    private RestTemplate template;

    public BackendService(RestTemplate template) {
        this.template = template;
    }

    @CircuitBreaker(name="backend", fallbackMethod="getFallbackResponse")
    public Optional<FrontendResponse> getResponse() {
        long startTimeMillis = System.currentTimeMillis();
        ResponseEntity<BackendResponse> entity = template.getForEntity("/backend", BackendResponse.class);
        if (entity.getStatusCode().is2xxSuccessful()) {
            long totalTime = System.currentTimeMillis() - startTimeMillis;
            return Optional.of(new FrontendResponse(false, totalTime, entity.getBody()));
        }

        return Optional.empty();
    }

    public Optional<FrontendResponse> getFallbackResponse(Throwable ex) {
        return Optional.of(new FrontendResponse(true, 0, new BackendResponse(0, new Date())));
    }
}