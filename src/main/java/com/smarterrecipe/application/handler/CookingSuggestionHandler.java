package com.smarterrecipe.application.handler;

import com.smarterrecipe.domain.engine.MatchingEngine;
import com.smarterrecipe.domain.model.RecipeModel;
import com.smarterrecipe.domain.service.RecipeService;
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

    private final RecipeService recipeService;
    private final MatchingEngine matchingEngine;

    @Transactional(readOnly = true)
    public List<SuggestionResponse> getSuggestions(CookingSuggestionRequest request) {
        List<RecipeModel> recipes = recipeService.getAllRecipes();
        Set<Long> ownedIds = Set.copyOf(request.getOwnedIngredientIds());

        return recipes.stream()
                .map(recipe -> evaluateRecipe(recipe, ownedIds))
                .sorted((a, b) -> Double.compare(b.getMatchScore(), a.getMatchScore()))
                .collect(Collectors.toList());
    }

    private SuggestionResponse evaluateRecipe(RecipeModel recipe, Set<Long> ownedIds) {
        List<Long> requiredIds = extractRequiredIngredients(recipe);
        List<Long> effectiveOwnedIds = resolveSubstitutions(recipe, ownedIds);

        MatchingEngine.MatchResult result = matchingEngine.evaluateRecipeMatch(effectiveOwnedIds, requiredIds);

        return buildResponse(recipe, result);
    }

    private List<Long> extractRequiredIngredients(RecipeModel recipe) {
        return recipe.getIngredients().stream()
                .map(RecipeModel.IngredientModel::getIngredientId)
                .collect(Collectors.toList());
    }

    private List<Long> resolveSubstitutions(RecipeModel recipe, Set<Long> ownedIds) {
        List<Long> effectiveOwned = new ArrayList<>(ownedIds);

        for (RecipeModel.IngredientModel ri : recipe.getIngredients()) {
            if (!ownedIds.contains(ri.getIngredientId()) && hasValidSubstitute(ri, ownedIds)) {
                effectiveOwned.add(ri.getIngredientId());
            }
        }
        return effectiveOwned;
    }

    private boolean hasValidSubstitute(RecipeModel.IngredientModel ri, Set<Long> ownedIds) {
        if (ri.getSubstituteIngredientIds() == null) return false;
        return ri.getSubstituteIngredientIds().stream().anyMatch(ownedIds::contains);
    }

    private SuggestionResponse buildResponse(RecipeModel recipe, MatchingEngine.MatchResult result) {
        return SuggestionResponse.builder()
                .id(recipe.getId())
                .title(recipe.getTitle())
                .preparationTime(recipe.getPreparationTime())
                .matchScore(result.matchScore())
                .missingIngredientIds(result.missingIngredientIds())
                .build();
    }
}