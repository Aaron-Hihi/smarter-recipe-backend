package com.smarterrecipe.presentation.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
@Builder
public class AuditLogResponse {
    private Long id;
    private String action;
    private Long adminId;
    private String adminUsername;
    private String targetEntityType;
    private Long targetEntityId;
    private String details;
    private LocalDateTime createdAt;
}
