package com.smarterrecipe.presentation.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@Builder
public class SuggestionResponse {
    private Long id;
    private String title;
    private String thumbnailUrl;
    private Integer preparationTime;
    private double matchScore;
    private List<Long> missingIngredientIds;
}