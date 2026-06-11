package com.smarterrecipe.domain.service;

import com.smarterrecipe.data.entity.AuditLog;
import com.smarterrecipe.data.entity.User;
import com.smarterrecipe.data.repository.AuditLogRepository;
import com.smarterrecipe.data.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuditLogService {

    private final AuditLogRepository auditLogRepository;
    private final UserRepository userRepository;

    @Transactional
    public AuditLog record(Long actorId, String action, String targetType, Long targetId, String details, String ipAddress) {
        User actor = userRepository.findById(actorId)
                .orElseThrow(() -> new IllegalArgumentException("Actor tidak ditemukan"));

        AuditLog auditLog = new AuditLog();
        auditLog.setActor(actor);
        auditLog.setAction(action);
        auditLog.setTargetType(targetType);
        auditLog.setTargetId(targetId);
        auditLog.setDetails(details);
        auditLog.setIpAddress(ipAddress);

        return auditLogRepository.save(auditLog);
    }

    public List<AuditLog> getLogsByActor(Long actorId) {
        return auditLogRepository.findAllByActorIdOrderByCreatedAtDesc(actorId);
    }

    public List<AuditLog> getLogsByTarget(String targetType, Long targetId) {
        return auditLogRepository.findAllByTargetTypeAndTargetIdOrderByCreatedAtDesc(targetType, targetId);
    }

    public List<AuditLog> getLogsByAction(String action) {
        return auditLogRepository.findAllByActionOrderByCreatedAtDesc(action);
    }
}
