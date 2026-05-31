package com.smarterrecipe.presentation.dto.dietarytag;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class DietaryTagResponse {
    private Long id;
    private String name;
    private String description;
}