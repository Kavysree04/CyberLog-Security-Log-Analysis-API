package com.cyberlog.securitylogapi.service;

import com.cyberlog.securitylogapi.model.AnalysisResult;
import com.cyberlog.securitylogapi.model.LoginEvent;
import com.cyberlog.securitylogapi.repository.LoginEventRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SecurityLogAnalysisService {

    private static final String EVENT_FAILURE = "LOGIN_FAILURE";
    private static final String EVENT_SUCCESS = "LOGIN_SUCCESS";
    private static final int BRUTE_FORCE_THRESHOLD = 5;
    private static final int WINDOW_MINUTES = 15;

    private final LoginEventRepository loginEventRepository;

    public SecurityLogAnalysisService(LoginEventRepository loginEventRepository) {
        this.loginEventRepository = loginEventRepository;
    }

    public AnalysisResult saveAndAnalyze(LoginEvent event) {
        if (event.getTimestamp() == null) {
            event.setTimestamp(LocalDateTime.now());
        }

        if (event.getStatus() == null || event.getStatus().isBlank()) {
            event.setStatus(event.getEventType());
        }

        LoginEvent savedEvent = loginEventRepository.save(event);
        return analyzeEvent(savedEvent);
    }

    public AnalysisResult analyzeEvent(LoginEvent event) {
        LocalDateTime windowStart = LocalDateTime.now().minusMinutes(WINDOW_MINUTES);

        long failedAttemptsByUser = loginEventRepository.countByUsernameAndEventTypeAndTimestampAfter(
                event.getUsername(), EVENT_FAILURE, windowStart);

        long failedAttemptsByIp = loginEventRepository.countBySourceIpAndEventTypeAndTimestampAfter(
                event.getSourceIp(), EVENT_FAILURE, windowStart);

        boolean bruteForceDetected = failedAttemptsByUser >= BRUTE_FORCE_THRESHOLD || failedAttemptsByIp >= BRUTE_FORCE_THRESHOLD;

        String riskLevel = "LOW";
        String message = "No suspicious activity detected.";

        if (bruteForceDetected) {
            riskLevel = "HIGH";
            message = "Repeated failed login attempts indicate a possible brute-force attack.";
        } else if (event.getEventType().equals(EVENT_SUCCESS) && failedAttemptsByUser > 0) {
            riskLevel = "MEDIUM";
            message = "User had recent failed attempts before a successful login.";
        }

        if (event.getEventType().equals(EVENT_SUCCESS)) {
            riskLevel = "LOW";
            message = "Successful login observed after risk review.";
        }

        return new AnalysisResult(
                event.getId(),
                event.getUsername(),
                event.getSourceIp(),
                riskLevel,
                bruteForceDetected,
                failedAttemptsByUser,
                failedAttemptsByIp,
                message,
                LocalDateTime.now()
        );
    }

    public List<LoginEvent> getAllEvents() {
        return loginEventRepository.findAll();
    }

    public List<AnalysisResult> getThreatSummary() {
        return loginEventRepository.findAll().stream()
                .map(this::analyzeEvent)
                .toList();
    }
}
