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

    @NotEmpty
    private List<String> ingredients;
}