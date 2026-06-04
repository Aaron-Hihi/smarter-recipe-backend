package com.smarterrecipe.presentation.dto;

import com.smarterrecipe.presentation.dto.user.UserResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@Builder
public class CreatorProfileResponse {
    private Long id;
    private UserResponse user;
    private String specialty;
    private List<RecipeResponse> topRecipes;
    private Boolean isFollowing;
}
