package com.vmware.resilience.backend;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Profile("backend")
@RestController
public class BackendController {

    private int maxWaitMillis = 100;
    private Random random = new Random();
    private MeterRegistry registry;

    public BackendController(MeterRegistry registry) {
        this.registry = registry;
    }

    @GetMapping("/backend")
    public BackendResponse getData() throws Exception {

        int waitTime = random.nextInt(maxWaitMillis);
        Thread.sleep(waitTime);

        registry.timer("backend.op").record(waitTime, TimeUnit.MILLISECONDS);
        return new BackendResponse(waitTime, new Date());
    }

    @GetMapping("/backend/maxwait/{maxWaitMillis}")
    public void setMaxWaitMillis(@PathVariable int maxWaitMillis) {
        this.maxWaitMillis = maxWaitMillis;
    }
}
