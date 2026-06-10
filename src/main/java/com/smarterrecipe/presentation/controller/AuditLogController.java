package com.smarterrecipe.presentation.controller;

import com.smarterrecipe.application.handler.AuditLogHandler;
import com.smarterrecipe.presentation.dto.auditlog.AuditLogResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/audit-logs")
@RequiredArgsConstructor
public class AuditLogController {

    private final AuditLogHandler auditLogHandler;

    @GetMapping("/me")
    public ResponseEntity<List<AuditLogResponse>> getMyLogs() {
        return ResponseEntity.ok(auditLogHandler.getMyLogs());
    }

    @GetMapping("/target")
    public ResponseEntity<List<AuditLogResponse>> getLogsByTarget(
            @RequestParam String targetType,
            @RequestParam Long targetId) {
        return ResponseEntity.ok(auditLogHandler.getLogsByTarget(targetType, targetId));
    }

    @GetMapping("/action")
    public ResponseEntity<List<AuditLogResponse>> getLogsByAction(@RequestParam String action) {
        return ResponseEntity.ok(auditLogHandler.getLogsByAction(action));
    }
}
