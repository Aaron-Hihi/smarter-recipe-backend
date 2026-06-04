package com.smarterrecipe.application.handler;

import com.smarterrecipe.data.entity.AuditLog;
import com.smarterrecipe.data.entity.User;
import com.smarterrecipe.data.repository.admin.AuditLogJpaRepository;
import com.smarterrecipe.data.repository.user.UserJpaRepository;
import com.smarterrecipe.domain.model.enums.Role;
import com.smarterrecipe.presentation.dto.AuditLogResponse;
import com.smarterrecipe.presentation.dto.user.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminHandler {

    private final UserJpaRepository userJpaRepository;
    private final AuditLogJpaRepository auditLogJpaRepository;
    private final UserHandler userHandler;

    @Transactional
    public UserResponse banUser(Long targetUserId, String adminUsername) {
        User admin = getAdminUser(adminUsername);
        User target = userJpaRepository.findById(targetUserId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        if (Boolean.TRUE.equals(target.getIsBanned())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User is already banned");
        }

        target.setIsBanned(true);
        userJpaRepository.save(target);

        logAction(admin, "BAN_USER", "User", target.getId(),
                "Banned user: " + target.getUsername());

        return userHandler.getProfile(target.getUsername());
    }

    @Transactional
    public UserResponse unbanUser(Long targetUserId, String adminUsername) {
        User admin = getAdminUser(adminUsername);
        User target = userJpaRepository.findById(targetUserId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        target.setIsBanned(false);
        userJpaRepository.save(target);

        logAction(admin, "UNBAN_USER", "User", target.getId(),
                "Unbanned user: " + target.getUsername());

        return userHandler.getProfile(target.getUsername());
    }

    @Transactional
    public UserResponse verifyCreator(Long targetUserId, String adminUsername) {
        User admin = getAdminUser(adminUsername);
        User target = userJpaRepository.findById(targetUserId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        target.setIsVerified(true);
        // Promote role if they are a regular home cook
        if (target.getRole() == Role.HOME_COOK) {
            target.setRole(Role.RECIPE_CREATOR);
        }
        userJpaRepository.save(target);

        logAction(admin, "VERIFY_CREATOR", "User", target.getId(),
                "Verified creator: " + target.getUsername());

        return userHandler.getProfile(target.getUsername());
    }

    @Transactional(readOnly = true)
    public List<AuditLogResponse> getAuditLogs() {
        return auditLogJpaRepository.findTop50ByOrderByCreatedAtDesc()
                .stream()
                .map(this::toLogResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<UserResponse> getAllUsers() {
        return userJpaRepository.findAll().stream()
                .map(u -> userHandler.getProfile(u.getUsername()))
                .collect(Collectors.toList());
    }

    private User getAdminUser(String adminUsername) {
        User admin = userJpaRepository.findByUsername(adminUsername)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Admin not found"));
        if (admin.getRole() != Role.ADMIN && admin.getRole() != Role.MODERATOR) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Insufficient permissions");
        }
        return admin;
    }

    private void logAction(User admin, String action, String entityType, Long entityId, String details) {
        AuditLog log = new AuditLog();
        log.setActor(admin);
        log.setAction(action);
        log.setTargetType(entityType);
        log.setTargetId(entityId);
        log.setDetails(details);
        auditLogJpaRepository.save(log);
    }

    private AuditLogResponse toLogResponse(AuditLog log) {
        return AuditLogResponse.builder()
                .id(log.getId())
                .action(log.getAction())
                .adminId(log.getActor() != null ? log.getActor().getId() : null)
                .adminUsername(log.getActor() != null ? log.getActor().getUsername() : null)
                .targetEntityType(log.getTargetType())
                .targetEntityId(log.getTargetId())
                .details(log.getDetails())
                .createdAt(log.getCreatedAt())
                .build();
    }
}
