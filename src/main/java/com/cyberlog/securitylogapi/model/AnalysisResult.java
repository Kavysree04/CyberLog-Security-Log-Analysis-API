package com.cyberlog.securitylogapi.model;

import java.time.LocalDateTime;

public class AnalysisResult {

    private String eventId;
    private String username;
    private String sourceIp;
    private String riskLevel;
    private boolean bruteForceDetected;
    private long failedAttemptsByUser;
    private long failedAttemptsByIp;
    private String message;
    private LocalDateTime analyzedAt;

    public AnalysisResult() {
    }

    public AnalysisResult(String eventId, String username, String sourceIp, String riskLevel,
                          boolean bruteForceDetected, long failedAttemptsByUser, long failedAttemptsByIp,
                          String message, LocalDateTime analyzedAt) {
        this.eventId = eventId;
        this.username = username;
        this.sourceIp = sourceIp;
        this.riskLevel = riskLevel;
        this.bruteForceDetected = bruteForceDetected;
        this.failedAttemptsByUser = failedAttemptsByUser;
        this.failedAttemptsByIp = failedAttemptsByIp;
        this.message = message;
        this.analyzedAt = analyzedAt;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSourceIp() {
        return sourceIp;
    }

    public void setSourceIp(String sourceIp) {
        this.sourceIp = sourceIp;
    }

    public String getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(String riskLevel) {
        this.riskLevel = riskLevel;
    }

    public boolean isBruteForceDetected() {
        return bruteForceDetected;
    }

    public void setBruteForceDetected(boolean bruteForceDetected) {
        this.bruteForceDetected = bruteForceDetected;
    }

    public long getFailedAttemptsByUser() {
        return failedAttemptsByUser;
    }

    public void setFailedAttemptsByUser(long failedAttemptsByUser) {
        this.failedAttemptsByUser = failedAttemptsByUser;
    }

    public long getFailedAttemptsByIp() {
        return failedAttemptsByIp;
    }

    public void setFailedAttemptsByIp(long failedAttemptsByIp) {
        this.failedAttemptsByIp = failedAttemptsByIp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getAnalyzedAt() {
        return analyzedAt;
    }

    public void setAnalyzedAt(LocalDateTime analyzedAt) {
        this.analyzedAt = analyzedAt;
    }
}
