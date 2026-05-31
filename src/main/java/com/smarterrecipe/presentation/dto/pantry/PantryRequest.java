package com.smarterrecipe.presentation.dto.pantry;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PantryRequest {
    @NotBlank
    private String name;

    private String description;
}