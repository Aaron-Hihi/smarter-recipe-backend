package com.smarterrecipe.application.handler;

import com.smarterrecipe.data.entity.*;
import com.smarterrecipe.data.repository.RecipeRepository;
import com.smarterrecipe.domain.engine.MatchingEngine;
import com.smarterrecipe.domain.model.enums.RecipeStatus;
import com.smarterrecipe.presentation.dto.CookingSuggestionRequest;
import com.smarterrecipe.presentation.dto.SuggestionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CookingSuggestionHandler {

    private final RecipeRepository recipeRepository;
    private final MatchingEngine matchingEngine;

    @Transactional(readOnly = true)
    public List<SuggestionResponse> getSuggestions(CookingSuggestionRequest request) {
        List<Recipe> recipes = recipeRepository.findByStatus(RecipeStatus.PUBLISHED);
        Set<Long> ownedIds = Set.copyOf(request.getOwnedIngredientIds());

        return recipes.stream()
                .map(recipe -> evaluateRecipe(recipe, ownedIds))
                .sorted((a, b) -> Double.compare(b.getMatchScore(), a.getMatchScore()))
                .collect(Collectors.toList());
    }

    private SuggestionResponse evaluateRecipe(Recipe recipe, Set<Long> ownedIds) {
        List<Long> requiredIds = extractRequiredIngredients(recipe);
        List<Long> effectiveOwnedIds = resolveSubstitutions(recipe, ownedIds);

        MatchingEngine.MatchResult result = matchingEngine.evaluateRecipeMatch(effectiveOwnedIds, requiredIds);

        return buildResponse(recipe, result);
    }

    private List<Long> extractRequiredIngredients(Recipe recipe) {
        return recipe.getRecipeIngredients().stream()
                .map(ri -> ri.getIngredient().getId())
                .collect(Collectors.toList());
    }

    private List<Long> resolveSubstitutions(Recipe recipe, Set<Long> ownedIds) {
        List<Long> effectiveOwned = new ArrayList<>(ownedIds);

        for (RecipeIngredient ri : recipe.getRecipeIngredients()) {
            if (!ownedIds.contains(ri.getIngredient().getId()) && hasValidSubstitute(ri, ownedIds)) {
                effectiveOwned.add(ri.getIngredient().getId());
            }
        }
        return effectiveOwned;
    }

    private boolean hasValidSubstitute(RecipeIngredient ri, Set<Long> ownedIds) {
        if (ri.getSubstitutions() == null) {
            return false;
        }
        return ri.getSubstitutions().stream()
                .anyMatch(sub -> ownedIds.contains(sub.getSubstituteIngredient().getId()));
    }

    private SuggestionResponse buildResponse(Recipe recipe, MatchingEngine.MatchResult result) {
        return SuggestionResponse.builder()
                .id(recipe.getId())
                .title(recipe.getTitle())
                .thumbnailUrl(recipe.getThumbnailUrl())
                .preparationTime(recipe.getPreparationTime())
                .matchScore(result.matchScore())
                .missingIngredientIds(result.missingIngredientIds())
                .build();
    }
}