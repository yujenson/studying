package com.jenson.studying.web.controller.monitor;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jenson.studying.common.core.domain.AjaxResult;
import com.jenson.studying.common.core.web.controller.BaseController;
import com.jenson.studying.common.oss.OssService;

@RestController
@RequestMapping("/monitor/health")
public class HealthController extends BaseController {

    private final OssService ossService;

    public HealthController(OssService ossService) {
        this.ossService = ossService;
    }

    @GetMapping
    public AjaxResult health() {
        Map<String, Object> payload = new HashMap<>();
        payload.put("status", "UP");
        payload.put("timestamp", LocalDateTime.now());
        return success(payload);
    }

    @GetMapping("/oss-url")
    public AjaxResult sampleObjectUrl(@RequestParam(defaultValue = "sample-object.txt") String objectKey) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("bucket", ossService.getBucket());
        payload.put("objectKey", objectKey);
        payload.put("url", ossService.buildObjectUrl(objectKey).toString());
        return success(payload);
    }
}
