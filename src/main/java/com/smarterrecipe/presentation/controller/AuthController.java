package com.smarterrecipe.presentation.controller;

import com.smarterrecipe.application.handler.AuthHandler;
import com.smarterrecipe.presentation.dto.auth.AuthResponse;
import com.smarterrecipe.presentation.dto.auth.LoginRequest;
import com.smarterrecipe.presentation.dto.auth.RegisterRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smarterrecipe.application.dto.AuthResult;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthController {

    private final AuthHandler authHandler;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest request) {
        AuthResult result = authHandler.register(request.getName(), request.getEmail(), request.getPassword());
        return ResponseEntity.ok(AuthResponse.builder().token(result.getToken()).user(result.getUser()).build());
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        AuthResult result = authHandler.login(request.getEmail(), request.getPassword());
        return ResponseEntity.ok(AuthResponse.builder().token(result.getToken()).user(result.getUser()).build());
    }
}