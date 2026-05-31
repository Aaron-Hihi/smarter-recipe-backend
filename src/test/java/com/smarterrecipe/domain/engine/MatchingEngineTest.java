package com.smarterrecipe.domain.engine;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MatchingEngineTest {

    private MatchingEngine matchingEngine;

    @BeforeEach
    void setUp() {
        matchingEngine = new MatchingEngine();
    }

    // SECTION: Perfect Match

    @Test
    void evaluateRecipeMatch_WhenUserHasAllIngredients_ShouldReturnHundredPercent() {
        List<Long> userIngredients = List.of(1L, 2L, 3L);
        List<Long> recipeIngredients = List.of(1L, 2L);

        MatchingEngine.MatchResult result = matchingEngine.evaluateRecipeMatch(userIngredients, recipeIngredients);

        assertEquals(100.0, result.matchScore());
        assertTrue(result.missingIngredientIds().isEmpty());
    }

    // SECTION: Partial Match

    @Test
    void evaluateRecipeMatch_WhenUserHasSomeIngredients_ShouldReturnCorrectPercentage() {
        List<Long> userIngredients = List.of(1L);
        List<Long> recipeIngredients = List.of(1L, 2L, 3L, 4L);

        MatchingEngine.MatchResult result = matchingEngine.evaluateRecipeMatch(userIngredients, recipeIngredients);

        assertEquals(25.0, result.matchScore());
        assertEquals(List.of(2L, 3L, 4L), result.missingIngredientIds());
    }

    @Test
    void evaluateRecipeMatch_WhenCalculationProducesDecimals_ShouldRoundProperly() {
        List<Long> userIngredients = List.of(1L, 2L);
        List<Long> recipeIngredients = List.of(1L, 2L, 3L);

        MatchingEngine.MatchResult result = matchingEngine.evaluateRecipeMatch(userIngredients, recipeIngredients);

        assertEquals(66.67, result.matchScore());
        assertEquals(List.of(3L), result.missingIngredientIds());
    }

    // SECTION: No Match

    @Test
    void evaluateRecipeMatch_WhenUserHasNoIngredients_ShouldReturnZeroPercent() {
        List<Long> userIngredients = List.of(5L, 6L);
        List<Long> recipeIngredients = List.of(1L, 2L);

        MatchingEngine.MatchResult result = matchingEngine.evaluateRecipeMatch(userIngredients, recipeIngredients);

        assertEquals(0.0, result.matchScore());
        assertEquals(List.of(1L, 2L), result.missingIngredientIds());
    }

    // SECTION: Edge Cases

    @Test
    void evaluateRecipeMatch_WhenRecipeRequiresNoIngredients_ShouldReturnHundredPercent() {
        List<Long> userIngredients = List.of(1L, 2L);
        List<Long> recipeIngredients = List.of();

        MatchingEngine.MatchResult result = matchingEngine.evaluateRecipeMatch(userIngredients, recipeIngredients);

        assertEquals(100.0, result.matchScore());
        assertTrue(result.missingIngredientIds().isEmpty());
    }

    @Test
    void evaluateRecipeMatch_WhenRecipeIngredientsAreNull_ShouldReturnHundredPercent() {
        List<Long> userIngredients = List.of(1L, 2L);

        MatchingEngine.MatchResult result = matchingEngine.evaluateRecipeMatch(userIngredients, null);

        assertEquals(100.0, result.matchScore());
        assertTrue(result.missingIngredientIds().isEmpty());
    }

    @Test
    void evaluateRecipeMatch_WhenUserIngredientsAreNull_ShouldTreatAsEmpty() {
        List<Long> recipeIngredients = List.of(1L, 2L);

        MatchingEngine.MatchResult result = matchingEngine.evaluateRecipeMatch(null, recipeIngredients);

        assertEquals(0.0, result.matchScore());
        assertEquals(List.of(1L, 2L), result.missingIngredientIds());
    }
}