package com.smarterrecipe.presentation.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import com.smarterrecipe.presentation.dto.user.UserResponse;

@Getter @Setter
@Builder
@AllArgsConstructor
public class AuthResponse {
    private String token;
    private UserResponse user;
}