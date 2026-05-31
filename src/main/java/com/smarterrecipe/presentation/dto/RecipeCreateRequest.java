package com.smarterrecipe.presentation.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter @Setter
public class RecipeCreateRequest {
    @NotBlank
    private String title;

    private String description;

    @NotNull
    private Integer preparationTime;

    @NotNull
    private Integer servingSize;

    @NotEmpty
    private List<StepRequest> steps;

    @NotEmpty
    private List<RecipeIngredientRequest> ingredients;

    private List<Long> pantryIds;
    private List<Long> dietaryTagIds;

    @Getter @Setter
    public static class StepRequest {
        private Integer stepNumber;
        private String instruction;
    }

    @Getter @Setter
    public static class RecipeIngredientRequest {
        private Long ingredientId;
        private Double amount;
        private String unit;
    }
}