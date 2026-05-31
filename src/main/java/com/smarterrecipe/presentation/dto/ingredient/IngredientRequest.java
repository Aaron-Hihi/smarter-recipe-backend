package com.smarterrecipe.presentation.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class IngredientRequest {
    @NotBlank
    private String name;
}