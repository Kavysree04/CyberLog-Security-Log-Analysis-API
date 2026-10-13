package com.cyberlog.securitylogapi.repository;

import com.cyberlog.securitylogapi.model.LoginEvent;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;

public interface LoginEventRepository extends MongoRepository<LoginEvent, String> {

    long countByUsernameAndEventTypeAndTimestampAfter(String username, String eventType, LocalDateTime after);

    long countBySourceIpAndEventTypeAndTimestampAfter(String sourceIp, String eventType, LocalDateTime after);
}
