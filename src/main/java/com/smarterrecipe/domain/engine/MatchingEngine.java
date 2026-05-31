package com.smarterrecipe.domain.engine;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class MatchingEngine {

    public MatchResult evaluateRecipeMatch(List<Long> userIngredientIds, List<Long> recipeIngredientIds) {
        if (recipeIngredientIds == null || recipeIngredientIds.isEmpty()) {
            return new MatchResult(100.0, List.of());
        }

        Set<Long> userInventory = (userIngredientIds == null) ? Set.of() : Set.copyOf(userIngredientIds);

        List<Long> missingIngredientIds = recipeIngredientIds.stream()
                .filter(id -> !userInventory.contains(id))
                .collect(Collectors.toList());

        double matchScore = calculatePercentage(recipeIngredientIds.size(), missingIngredientIds.size());

        return new MatchResult(matchScore, missingIngredientIds);
    }

    private double calculatePercentage(int totalRequired, int totalMissing) {
        double existingCount = totalRequired - totalMissing;
        double score = (existingCount / totalRequired) * 100.0;
        return Math.round(score * 100.0) / 100.0;
    }

    public record MatchResult(double matchScore, List<Long> missingIngredientIds) {}
}