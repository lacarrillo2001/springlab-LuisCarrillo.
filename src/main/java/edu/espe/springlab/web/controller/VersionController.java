package edu.espe.springlab.web.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class VersionController {

    @Value("${app.version}")
    private String appVersion;

    @GetMapping("/version")
    public Map<String, String> getVersion() {
        return Map.of("version", appVersion);
    }
}

