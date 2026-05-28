package com.smarterrecipe.application.handler;

import org.springframework.stereotype.Component;

@Component
public class HealthCheckHandler {

    public String getStatusMessage() {

        return "Smarter Recipe Backend is Running Perfectly";
    }
}