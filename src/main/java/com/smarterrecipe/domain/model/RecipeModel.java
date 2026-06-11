package com.smarterrecipe.domain.model;

import com.smarterrecipe.domain.model.enums.RecipeStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class RecipeModel {
    private Long id;
    private Long creatorId;
    private String creatorUsername;
    private String title;
    private String thumbnailUrl;
    private String description;
    private Integer preparationTime;
    private Integer servingSize;
    private Double averageRating;
    private Integer totalReviews;
    private RecipeStatus status;
    private java.time.LocalDateTime createdAt;
    private List<String> dietaryTags;
    private List<StepModel> steps;
    private List<IngredientModel> ingredients;
    private List<String> tools;

    @Getter @Setter @Builder
    public static class StepModel {
        private Integer stepNumber;
        private String instruction;
    }

    @Getter @Setter @Builder
    public static class IngredientModel {
        private Long ingredientId;
        private String ingredientName;
        private Double amount;
        private String unit;
        private List<Long> substituteIngredientIds;
    }
}