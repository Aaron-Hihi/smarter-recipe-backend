package com.smarterrecipe.presentation.dto.socialgraph;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class FollowResponse {
    private Long id;
    private Long userId;
    private String username;
    private String profilePictureUrl;
    private LocalDateTime followedAt;
}
