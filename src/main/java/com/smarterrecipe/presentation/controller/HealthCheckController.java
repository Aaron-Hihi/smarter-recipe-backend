package com.smarterrecipe.presentation.controller;

import com.smarterrecipe.application.handler.HealthCheckHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/health")
public class HealthCheckController {

    private final HealthCheckHandler healthCheckHandler;

    public HealthCheckController(HealthCheckHandler healthCheckHandler) {
        this.healthCheckHandler = healthCheckHandler;
    }

    @GetMapping
    public String checkHealth() {
        return healthCheckHandler.getStatusMessage();
    }
}