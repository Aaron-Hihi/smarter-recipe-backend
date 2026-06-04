package com.smarterrecipe.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
@Builder
@AllArgsConstructor
public class ReviewResponse {
    private Long id;
    private Long recipeId;
    private Long userId;
    private String username;
    private String profileImageUrl;
    private Integer rating;
    private String comment;
    private LocalDateTime createdAt;
}
