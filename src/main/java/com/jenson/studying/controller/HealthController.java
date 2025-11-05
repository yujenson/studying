package com.jenson.studying.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jenson.studying.service.OssService;

@RestController
@RequestMapping("/api")
public class HealthController {

    private final OssService ossService;

    public HealthController(OssService ossService) {
        this.ossService = ossService;
    }

    @GetMapping("/health")
    public Map<String, Object> health() {
        Map<String, Object> payload = new HashMap<>();
        payload.put("status", "UP");
        payload.put("timestamp", LocalDateTime.now());
        return payload;
    }

    @GetMapping("/oss/sample-url")
    public Map<String, Object> sampleObjectUrl(@RequestParam(defaultValue = "sample-object.txt") String objectKey) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("bucket", ossService.getBucket());
        payload.put("objectKey", objectKey);
        payload.put("url", ossService.buildObjectUrl(objectKey).toString());
        return payload;
    }
}
