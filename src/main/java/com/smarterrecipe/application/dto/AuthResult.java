package com.smarterrecipe.application.dto;

import com.smarterrecipe.domain.model.UserModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Builder
@AllArgsConstructor
public class AuthResult {
    private String token;
    private UserModel user;
}
