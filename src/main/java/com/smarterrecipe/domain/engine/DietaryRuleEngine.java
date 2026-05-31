package com.smarterrecipe.domain.engine;

import com.smarterrecipe.data.repository.DietaryIngredientRestrictionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DietaryRuleEngine {

    private final DietaryIngredientRestrictionRepository restrictionRepository;

    public void validateDietaryTags(List<Long> ingredientIds, List<Long> dietaryTagIds) {
        if (dietaryTagIds == null || dietaryTagIds.isEmpty() || ingredientIds == null) {
            return;
        }

        for (Long tagId : dietaryTagIds) {
            for (Long ingredientId : ingredientIds) {
                if (restrictionRepository.existsByDietaryTagIdAndIngredientId(tagId, ingredientId)) {
                    throw new IllegalArgumentException(
                            "Dietary Rule Violation: Ingredient ID " + ingredientId +
                                    " is strictly restricted for Dietary Tag ID " + tagId
                    );
                }
            }
        }
    }
}