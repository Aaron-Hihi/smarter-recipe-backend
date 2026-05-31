package com.smarterrecipe.application.handler;

import com.smarterrecipe.data.entity.*;
import com.smarterrecipe.data.repository.*;
import com.smarterrecipe.domain.model.enums.RecipeStatus;
import com.smarterrecipe.presentation.dto.RecipeCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

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

    @Transactional
    public String createRecipe(RecipeCreateRequest request) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User creator = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Recipe recipe = new Recipe();
        recipe.setCreator(creator);
        recipe.setTitle(request.getTitle());
        recipe.setDescription(request.getDescription());
        recipe.setPreparationTime(request.getPreparationTime());
        recipe.setServingSize(request.getServingSize());
        recipe.setStatus(RecipeStatus.PUBLISHED);
        recipe.setCreatedAt(LocalDateTime.now());
        recipe = recipeRepository.save(recipe);

        for (RecipeCreateRequest.StepRequest stepReq : request.getSteps()) {
            RecipeStep step = new RecipeStep();
            step.setRecipe(recipe);
            step.setStepNumber(stepReq.getStepNumber());
            step.setInstruction(stepReq.getInstruction());
            recipeStepRepository.save(step);
        }

        for (RecipeCreateRequest.RecipeIngredientRequest ingReq : request.getIngredients()) {
            Ingredient ingredient = ingredientRepository.findById(ingReq.getIngredientId())
                    .orElseThrow(() -> new IllegalArgumentException("Ingredient ID " + ingReq.getIngredientId() + " not found"));

            RecipeIngredient recipeIngredient = new RecipeIngredient();
            recipeIngredient.setRecipe(recipe);
            recipeIngredient.setIngredient(ingredient);
            recipeIngredient.setAmount(ingReq.getAmount());
            recipeIngredient.setUnit(ingReq.getUnit());
            recipeIngredientRepository.save(recipeIngredient);
        }

        if (request.getPantryIds() != null) {
            for (Long pantryId : request.getPantryIds()) {
                Pantry pantry = pantryRepository.findById(pantryId)
                        .orElseThrow(() -> new IllegalArgumentException("Pantry ID " + pantryId + " not found"));

                RecipePantry recipePantry = new RecipePantry();
                recipePantry.setRecipe(recipe);
                recipePantry.setPantry(pantry);
                recipePantryRepository.save(recipePantry);
            }
        }

        if (request.getDietaryTagIds() != null) {
            for (Long tagId : request.getDietaryTagIds()) {
                DietaryTag tag = dietaryTagRepository.findById(tagId)
                        .orElseThrow(() -> new IllegalArgumentException("Dietary Tag ID " + tagId + " not found"));

                RecipeDietaryTag recipeTag = new RecipeDietaryTag();
                recipeTag.setRecipe(recipe);
                recipeTag.setDietaryTag(tag);
                recipeDietaryTagRepository.save(recipeTag);
            }
        }

        return "Recipe successfully created with ID: " + recipe.getId();
    }
}