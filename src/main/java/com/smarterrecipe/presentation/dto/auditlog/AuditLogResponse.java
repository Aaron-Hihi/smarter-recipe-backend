package com.smarterrecipe.presentation.dto.auditlog;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class AuditLogResponse {
    private Long id;
    private Long actorId;
    private String actorUsername;
    private String action;
    private String targetType;
    private Long targetId;
    private String details;
    private String ipAddress;
    private LocalDateTime createdAt;
}
