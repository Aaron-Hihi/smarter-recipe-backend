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
    private String description;
    private String creatorName;
    private Integer preparationTime;
    private Integer servingSize;
    private String status;
    private List<StepResponse> steps;
    private List<IngredientResponse> ingredients;

    @Getter @Setter
    @Builder
    public static class StepResponse {
        private Integer stepNumber;
        private String instruction;
    }

    @Getter @Setter
    @Builder
    public static class IngredientResponse {
        private String ingredientName;
        private Double amount;
        private String unit;
    }
}