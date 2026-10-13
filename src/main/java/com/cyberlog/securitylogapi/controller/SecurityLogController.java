package com.cyberlog.securitylogapi.controller;

import com.cyberlog.securitylogapi.model.AnalysisResult;
import com.cyberlog.securitylogapi.model.LoginEvent;
import com.cyberlog.securitylogapi.repository.LoginEventRepository;
import com.cyberlog.securitylogapi.service.SecurityLogAnalysisService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/security")
public class SecurityLogController {

    private final SecurityLogAnalysisService securityLogAnalysisService;
    private final LoginEventRepository loginEventRepository;

    public SecurityLogController(SecurityLogAnalysisService securityLogAnalysisService,
                                LoginEventRepository loginEventRepository) {
        this.securityLogAnalysisService = securityLogAnalysisService;
        this.loginEventRepository = loginEventRepository;
    }

    @PostMapping("/login-events")
    public ResponseEntity<AnalysisResult> analyzeLoginEvent(@Valid @RequestBody LoginEvent loginEvent) {
        AnalysisResult result = securityLogAnalysisService.saveAndAnalyze(loginEvent);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/events")
    public List<LoginEvent> getAllEvents() {
        return securityLogAnalysisService.getAllEvents();
    }

    @GetMapping("/threats")
    public List<AnalysisResult> getThreatSummary() {
        return securityLogAnalysisService.getThreatSummary();
    }

    @GetMapping("/health")
    public Map<String, String> health() {
        return Map.of("status", "UP", "service", "CyberLog Security Log Analysis API");
    }
}
