package com.example.codeexecutioner.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for health check endpoint.
 */
@RestController
@RequestMapping("/health")
public class HealthController {

    /**
     * Health check endpoint.
     * @return A simple string confirming the service is running.
     */
    @GetMapping
    public String healthCheck() {
        return "Service is running";
    }
}