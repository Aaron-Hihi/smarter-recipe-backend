package com.smarterrecipe.application.handler;

import com.smarterrecipe.data.entity.AuditLog;
import com.smarterrecipe.data.entity.User;
import com.smarterrecipe.data.repository.user.UserJpaRepository;
import com.smarterrecipe.domain.service.AuditLogService;
import com.smarterrecipe.presentation.dto.auditlog.AuditLogResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuditLogHandler {

    private final AuditLogService auditLogService;
    private final UserJpaRepository userRepository;

    public void log(String action, String targetType, Long targetId, String details, HttpServletRequest request) {
        Long actorId = getAuthenticatedUserId();
        String ipAddress = extractIpAddress(request);
        auditLogService.record(actorId, action, targetType, targetId, details, ipAddress);
    }

    public List<AuditLogResponse> getMyLogs() {
        Long actorId = getAuthenticatedUserId();
        return auditLogService.getLogsByActor(actorId).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public List<AuditLogResponse> getLogsByTarget(String targetType, Long targetId) {
        return auditLogService.getLogsByTarget(targetType, targetId).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public List<AuditLogResponse> getLogsByAction(String action) {
        return auditLogService.getLogsByAction(action).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private Long getAuthenticatedUserId() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User tidak ditemukan"));
        return user.getId();
    }

    private String extractIpAddress(HttpServletRequest request) {
        String forwarded = request.getHeader("X-Forwarded-For");
        if (forwarded != null && !forwarded.isEmpty()) {
            return forwarded.split(",")[0].trim();
        }
        return request.getRemoteAddr();
    }

    private AuditLogResponse mapToResponse(AuditLog auditLog) {
        return new AuditLogResponse(
                auditLog.getId(),
                auditLog.getActor().getId(),
                auditLog.getActor().getUsername(),
                auditLog.getAction(),
                auditLog.getTargetType(),
                auditLog.getTargetId(),
                auditLog.getDetails(),
                auditLog.getIpAddress(),
                auditLog.getCreatedAt()
        );
    }
}
