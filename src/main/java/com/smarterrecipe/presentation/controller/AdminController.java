package com.smarterrecipe.presentation.controller;

import com.smarterrecipe.application.handler.AdminHandler;
import com.smarterrecipe.presentation.dto.ApiResponse;
import com.smarterrecipe.presentation.dto.AuditLogResponse;
import com.smarterrecipe.presentation.dto.user.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
@PreAuthorize("hasAnyRole('ADMIN', 'MODERATOR')")
public class AdminController {

    private final AdminHandler adminHandler;

    @GetMapping("/users")
    public ResponseEntity<ApiResponse<List<UserResponse>>> getAllUsers() {
        return ResponseEntity.ok(new ApiResponse<>(adminHandler.getAllUsers()));
    }

    @PostMapping("/users/{id}/ban")
    public ResponseEntity<ApiResponse<UserResponse>> banUser(@PathVariable Long id, Principal principal) {
        return ResponseEntity.ok(new ApiResponse<>(adminHandler.banUser(id, principal.getName())));
    }

    @PostMapping("/users/{id}/unban")
    public ResponseEntity<ApiResponse<UserResponse>> unbanUser(@PathVariable Long id, Principal principal) {
        return ResponseEntity.ok(new ApiResponse<>(adminHandler.unbanUser(id, principal.getName())));
    }

    @PostMapping("/creators/{id}/verify")
    public ResponseEntity<ApiResponse<UserResponse>> verifyCreator(@PathVariable Long id, Principal principal) {
        return ResponseEntity.ok(new ApiResponse<>(adminHandler.verifyCreator(id, principal.getName())));
    }

    @GetMapping("/logs")
    public ResponseEntity<ApiResponse<List<AuditLogResponse>>> getAuditLogs() {
        return ResponseEntity.ok(new ApiResponse<>(adminHandler.getAuditLogs()));
    }
}
