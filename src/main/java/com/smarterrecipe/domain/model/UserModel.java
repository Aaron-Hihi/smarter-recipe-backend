package com.smarterrecipe.domain.model;

import com.smarterrecipe.domain.model.enums.Role;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserModel {
    private Long id;
    private String username;
    private String email;
    private String password;
    private Role role;
    private String profilePictureUrl;
    private java.time.LocalDateTime createdAt;
}