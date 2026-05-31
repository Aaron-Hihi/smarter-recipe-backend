package com.smarterrecipe.presentation.dto.dietarytag;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class DietaryTagRequest {
    @NotBlank
    private String name;

    private String description;
}