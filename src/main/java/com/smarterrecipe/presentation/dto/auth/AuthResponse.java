package com.smarterrecipe.presentation.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import com.smarterrecipe.domain.model.UserModel;

@Getter @Setter
@Builder
@AllArgsConstructor
public class AuthResponse {
    private String token;
    private UserModel user;
}