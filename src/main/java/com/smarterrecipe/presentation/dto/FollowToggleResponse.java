package com.smarterrecipe.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class FollowToggleResponse {
    private String message;
    private Boolean isFollowing;
}
