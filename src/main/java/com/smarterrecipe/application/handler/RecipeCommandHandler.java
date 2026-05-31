package com.smarterrecipe.application.handler;

import com.smarterrecipe.data.entity.*;
import com.smarterrecipe.data.repository.*;
import com.smarterrecipe.domain.engine.DietaryRuleEngine;
import com.smarterrecipe.domain.model.enums.RecipeStatus;
import com.smarterrecipe.presentation.dto.RecipeCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecipeCommandHandler {

    private final RecipeRepository recipeRepository;
    private final UserRepository userRepository;
    private final IngredientRepository ingredientRepository;
    private final PantryRepository pantryRepository;
    private final DietaryTagRepository dietaryTagRepository;
    private final RecipeStepRepository recipeStepRepository;
    private final RecipeIngredientRepository recipeIngredientRepository;
    private final RecipePantryRepository recipePantryRepository;
    private final RecipeDietaryTagRepository recipeDietaryTagRepository;

    private final DietaryRuleEngine dietaryRuleEngine;

    @Transactional
    public String createRecipe(RecipeCreateRequest request) {
        User creator = getCurrentAuthenticatedUser();

        List<Long> ingredientIds = extractIngredientIds(request);
        dietaryRuleEngine.validateDietaryTags(ingredientIds, request.getDietaryTagIds());

        Recipe recipe = initializeBaseRecipe(request, creator);
        recipe = recipeRepository.save(recipe);

        saveRecipeSteps(recipe, request.getSteps());
        saveRecipeIngredients(recipe, request.getIngredients());
        saveRecipePantries(recipe, request.getPantryIds());
        saveRecipeDietaryTags(recipe, request.getDietaryTagIds());

        return "Recipe successfully created with ID: " + recipe.getId();
    }

    private User getCurrentAuthenticatedUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    private List<Long> extractIngredientIds(RecipeCreateRequest request) {
        return request.getIngredients().stream()
                .map(RecipeCreateRequest.RecipeIngredientRequest::getIngredientId)
                .collect(Collectors.toList());
    }

    private Recipe initializeBaseRecipe(RecipeCreateRequest request, User creator) {
        Recipe recipe = new Recipe();
        recipe.setCreator(creator);
        recipe.setTitle(request.getTitle());
        recipe.setDescription(request.getDescription());
        recipe.setPreparationTime(request.getPreparationTime());
        recipe.setServingSize(request.getServingSize());
        recipe.setStatus(RecipeStatus.PUBLISHED);
        recipe.setCreatedAt(LocalDateTime.now());
        return recipe;
    }

    private void saveRecipeSteps(Recipe recipe, List<RecipeCreateRequest.StepRequest> steps) {
        for (RecipeCreateRequest.StepRequest stepReq : steps) {
            RecipeStep step = new RecipeStep();
            step.setRecipe(recipe);
            step.setStepNumber(stepReq.getStepNumber());
            step.setInstruction(stepReq.getInstruction());
            recipeStepRepository.save(step);
        }
    }

    private void saveRecipeIngredients(Recipe recipe, List<RecipeCreateRequest.RecipeIngredientRequest> ingredients) {
        for (RecipeCreateRequest.RecipeIngredientRequest ingReq : ingredients) {
            Ingredient ingredient = ingredientRepository.findById(ingReq.getIngredientId())
                    .orElseThrow(() -> new IllegalArgumentException("Ingredient not found"));

            RecipeIngredient recipeIngredient = new RecipeIngredient();
            recipeIngredient.setRecipe(recipe);
            recipeIngredient.setIngredient(ingredient);
            recipeIngredient.setAmount(ingReq.getAmount());
            recipeIngredient.setUnit(ingReq.getUnit());
            recipeIngredientRepository.save(recipeIngredient);
        }
    }

    private void saveRecipePantries(Recipe recipe, List<Long> pantryIds) {
        if (pantryIds == null) return;
        for (Long pantryId : pantryIds) {
            Pantry pantry = pantryRepository.findById(pantryId)
                    .orElseThrow(() -> new IllegalArgumentException("Pantry not found"));

            RecipePantry recipePantry = new RecipePantry();
            recipePantry.setRecipe(recipe);
            recipePantry.setPantry(pantry);
            recipePantryRepository.save(recipePantry);
        }
    }

    private void saveRecipeDietaryTags(Recipe recipe, List<Long> tagIds) {
        if (tagIds == null) return;
        for (Long tagId : tagIds) {
            DietaryTag tag = dietaryTagRepository.findById(tagId)
                    .orElseThrow(() -> new IllegalArgumentException("Dietary Tag not found"));

            RecipeDietaryTag recipeTag = new RecipeDietaryTag();
            recipeTag.setRecipe(recipe);
            recipeTag.setDietaryTag(tag);
            recipeDietaryTagRepository.save(recipeTag);
        }
    }
}