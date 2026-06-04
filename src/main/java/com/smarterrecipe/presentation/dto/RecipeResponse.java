package com.smarterrecipe.presentation.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@Builder
public class RecipeResponse {
    private Long id;
    private String title;
    private String authorName;
    private Long authorId;
    private String cookTime;
    private Integer servings;
    private Double averageRating;
    private String imageUrl;
    private Integer matchScore;
    private Boolean isPublished;
    private List<String> dietaryTags;
    private List<IngredientResponse> ingredients;
    private List<String> steps;

    @Getter @Setter
    @Builder
    public static class IngredientResponse {
        private Long id;
        private String name;
        private String quantity;
        private Boolean isMissing;
    }
}