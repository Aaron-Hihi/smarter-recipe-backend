package com.smarterrecipe.application.handler;

import com.smarterrecipe.presentation.dto.CookingSuggestionRequest;
import com.smarterrecipe.presentation.dto.SuggestionResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Transactional
@Sql(scripts = "/data.sql")
class CookingSuggestionHandlerIntegrationTest {

    @Autowired
    private CookingSuggestionHandler cookingSuggestionHandler;

    @Test
    void getSuggestions_WithPartialIngredients_ShouldReturnCorrectScore() {
        CookingSuggestionRequest request = new CookingSuggestionRequest();
        request.setOwnedIngredientIds(List.of(21L, 17L, 7L));

        List<SuggestionResponse> responses = cookingSuggestionHandler.getSuggestions(request);

        SuggestionResponse steakRecipe = responses.stream()
                .filter(r -> r.getId() == 5L)
                .findFirst()
                .orElseThrow();

        assertEquals(75.0, steakRecipe.getMatchScore());
        assertTrue(steakRecipe.getMissingIngredientIds().contains(25L));
    }

    @Test
    void getSuggestions_WithSubstituteIngredient_ShouldReturnHundredPercent() {
        CookingSuggestionRequest request = new CookingSuggestionRequest();
        request.setOwnedIngredientIds(List.of(3L, 18L, 17L, 4L, 2L, 7L));

        List<SuggestionResponse> responses = cookingSuggestionHandler.getSuggestions(request);

        SuggestionResponse nasiGoreng = responses.stream()
                .filter(r -> r.getId() == 1L)
                .findFirst()
                .orElseThrow();

        assertEquals(100.0, nasiGoreng.getMatchScore());
        assertTrue(nasiGoreng.getMissingIngredientIds().isEmpty());
    }

    @Test
    void getSuggestions_WithNoIngredients_ShouldReturnZeroPercent() {
        CookingSuggestionRequest request = new CookingSuggestionRequest();
        request.setOwnedIngredientIds(List.of());

        List<SuggestionResponse> responses = cookingSuggestionHandler.getSuggestions(request);

        SuggestionResponse soupRecipe = responses.stream()
                .filter(r -> r.getId() == 3L)
                .findFirst()
                .orElseThrow();

        assertEquals(0.0, soupRecipe.getMatchScore());
        assertEquals(4, soupRecipe.getMissingIngredientIds().size());
    }
}