package com.smarterrecipe.domain.engine;

import com.smarterrecipe.domain.repository.DietaryRestrictionRepository;
import com.smarterrecipe.domain.model.RecipeModel;
import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class DietaryRuleEngine {

    private final DietaryRestrictionRepository restrictionRepository;

    public void validateDietaryTags(List<Long> ingredientIds, List<Long> dietaryTagIds) {
        if (dietaryTagIds == null || dietaryTagIds.isEmpty() || ingredientIds == null || ingredientIds.isEmpty()) {
            return;
        }

        List<Long> violatingIds = restrictionRepository.findViolatingIngredients(dietaryTagIds, ingredientIds);

        if (!violatingIds.isEmpty()) {
            throw new IllegalArgumentException(
                    "Dietary Rule Violation: Ingredient IDs " + violatingIds + " are strictly restricted."
            );
        }
    }

    public List<RecipeModel> filterByTag(List<RecipeModel> recipes, String tagName) {
        return recipes.stream()
                .filter(recipe -> hasDietaryTag(recipe, tagName))
                .collect(Collectors.toList());
    }

    private boolean hasDietaryTag(RecipeModel recipe, String tagName) {
        if (recipe.getDietaryTags() == null) {
            return false;
        }
        return recipe.getDietaryTags().stream()
                .anyMatch(tag -> tag.equalsIgnoreCase(tagName));
    }
}