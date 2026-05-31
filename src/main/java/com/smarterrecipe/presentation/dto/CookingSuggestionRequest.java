package com.smarterrecipe.presentation.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class CookingSuggestionRequest {
    @NotEmpty
    private List<Long> ownedIngredientIds;
}