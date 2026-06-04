package com.smarterrecipe.presentation.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ProfileUpdateRequest {
    private String name;
    private String bio;
}
