package com.smarterrecipe.presentation.controller;

import com.smarterrecipe.application.handler.UserHandler;
import com.smarterrecipe.presentation.dto.ApiResponse;
import com.smarterrecipe.presentation.dto.DietaryPreferencesRequest;
import com.smarterrecipe.presentation.dto.ProfileUpdateRequest;
import com.smarterrecipe.presentation.dto.user.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/profile")
@RequiredArgsConstructor
public class ProfileController {

    private final UserHandler userHandler;

    @GetMapping
    public ResponseEntity<ApiResponse<UserResponse>> getProfile(Principal principal) {
        return ResponseEntity.ok(new ApiResponse<>(userHandler.getProfile(principal.getName())));
    }

    @PutMapping
    public ResponseEntity<ApiResponse<UserResponse>> updateProfile(@RequestBody ProfileUpdateRequest request, Principal principal) {
        return ResponseEntity.ok(new ApiResponse<>(userHandler.updateProfile(principal.getName(), request.getName(), request.getBio())));
    }

    @PutMapping("/dietary")
    public ResponseEntity<ApiResponse<UserResponse>> updateDietaryPreferences(@RequestBody DietaryPreferencesRequest request, Principal principal) {
        return ResponseEntity.ok(new ApiResponse<>(userHandler.updateDietaryPreferences(principal.getName(), request)));
    }
}
