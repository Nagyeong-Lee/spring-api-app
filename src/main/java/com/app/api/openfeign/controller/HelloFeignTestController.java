package com.app.api.openfeign.controller;

import com.app.api.health.dto.HealthCheckResponseDto;
import com.app.api.openfeign.client.HelloClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class HelloFeignTestController {

    private final HelloClient helloClient;

    @GetMapping("/health/feign-test")
    public ResponseEntity<HealthCheckResponseDto> healthCheck() {
        HealthCheckResponseDto healthCheckResponseDto = helloClient.healthCheck();
        return ResponseEntity.ok(healthCheckResponseDto);
    }
}
