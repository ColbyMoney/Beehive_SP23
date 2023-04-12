package com.example.ProjectBeehive.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.HealthComponent;
import org.springframework.boot.actuate.health.HealthEndpoint;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health")
public class HealthController {

    private final HealthEndpoint healthEndpoint;

    @Autowired
    public HealthController(HealthEndpoint healthEndpoint) {
        this.healthEndpoint = healthEndpoint;
    }

    @GetMapping
    public ResponseEntity<String> getHealthStatus() {
        HealthComponent healthComponent = healthEndpoint.health();
        HttpStatus httpStatus = healthComponent.getStatus().equals(org.springframework.boot.actuate.health.Status.UP) ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR;

        ObjectMapper objectMapper = new ObjectMapper();
        String healthInfo;
        try {
            healthInfo = objectMapper.writeValueAsString(healthComponent);
        } catch (JsonProcessingException e) {
            healthInfo = "Error processing health information.";
        }

        return new ResponseEntity<>(healthInfo, httpStatus);
    }
}
