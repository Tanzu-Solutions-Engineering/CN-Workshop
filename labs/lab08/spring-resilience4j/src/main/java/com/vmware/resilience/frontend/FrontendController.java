package com.vmware.resilience.frontend;

import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Profile("frontend")
@RestController
public class FrontendController {

    private BackendService service;

    public FrontendController(BackendService service) {
        this.service = service;
    }

    @GetMapping("/frontend")
    public ResponseEntity<FrontendResponse> getResponse() {
        Optional<FrontendResponse> response = service.getResponse();
        if (response.isPresent()) {
            return ResponseEntity.ok(response.get());
        } else {
            return ResponseEntity.status(503).build();
        }
    }

    @GetMapping("/anerror")
    public ResponseEntity<FrontendError> getAnError() {
        return ResponseEntity
                .status(500)
                .body(new FrontendError("this-is-an-error"));
    }
}
