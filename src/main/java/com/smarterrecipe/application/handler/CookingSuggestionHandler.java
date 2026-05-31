package com.smarterrecipe.application.handler;

import com.smarterrecipe.data.entity.Recipe;
import com.smarterrecipe.data.entity.User;
import com.smarterrecipe.data.repository.RecipeRepository;
import com.smarterrecipe.data.repository.UserRepository;
import com.smarterrecipe.domain.model.enums.RecipeStatus;
import com.smarterrecipe.presentation.dto.RecipeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CookingSuggestionHandler {

    private final RecipeRepository recipeRepository;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<RecipeResponse> getCookingSuggestions() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        List<Recipe> suggestedRecipes = recipeRepository.findSuggestionsByUserId(user.getId(), RecipeStatus.PUBLISHED);

        return suggestedRecipes.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private RecipeResponse mapToResponse(Recipe recipe) {
        List<RecipeResponse.StepResponse> steps = recipe.getRecipeSteps() != null ?
                recipe.getRecipeSteps().stream()
                        .map(step -> RecipeResponse.StepResponse.builder()
                                .stepNumber(step.getStepNumber())
                                .instruction(step.getInstruction())
                                .build())
                        .collect(Collectors.toList()) : List.of();

        List<RecipeResponse.IngredientResponse> ingredients = recipe.getRecipeIngredients() != null ?
                recipe.getRecipeIngredients().stream()
                        .map(ing -> RecipeResponse.IngredientResponse.builder()
                                .ingredientName(ing.getIngredient().getName())
                                .amount(ing.getAmount())
                                .unit(ing.getUnit())
                                .build())
                        .collect(Collectors.toList()) : List.of();

        return RecipeResponse.builder()
                .id(recipe.getId())
                .title(recipe.getTitle())
                .description(recipe.getDescription())
                .creatorName(recipe.getCreator().getUsername())
                .preparationTime(recipe.getPreparationTime())
                .servingSize(recipe.getServingSize())
                .status(recipe.getStatus().name())
                .steps(steps)
                .ingredients(ingredients)
                .build();
    }
}