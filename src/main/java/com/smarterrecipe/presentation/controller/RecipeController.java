package com.smarterrecipe.presentation.controller;

import com.smarterrecipe.application.handler.RecipeCommandHandler;
import com.smarterrecipe.application.handler.RecipeQueryHandler;
import com.smarterrecipe.presentation.dto.RecipeCreateRequest;
import com.smarterrecipe.presentation.dto.RecipeResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

import com.smarterrecipe.presentation.dto.ApiResponse;

@RestController
@RequestMapping("/api/v1/recipes")
@RequiredArgsConstructor
public class RecipeController {

    private final RecipeCommandHandler recipeCommandHandler;
    private final RecipeQueryHandler recipeQueryHandler;

    @PostMapping
    public ResponseEntity<ApiResponse<RecipeResponse>> createRecipe(@Valid @RequestBody RecipeCreateRequest request, Principal principal) {
        com.smarterrecipe.domain.model.RecipeModel created = recipeCommandHandler.createRecipe(request.getTitle(), request.getIngredients(), request.getTools(), principal.getName());
        return ResponseEntity.ok(new ApiResponse<>(recipeQueryHandler.getRecipeById(created.getId())));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<RecipeResponse>>> getAllRecipes() {
        return ResponseEntity.ok(new ApiResponse<>(recipeQueryHandler.getAllRecipes()));
    }

    @GetMapping("/my")
    public ResponseEntity<ApiResponse<List<RecipeResponse>>> getMyRecipes() {
        return ResponseEntity.ok(new ApiResponse<>(recipeQueryHandler.getMyRecipes()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<RecipeResponse>> getRecipeById(@PathVariable Long id) {
        return ResponseEntity.ok(new ApiResponse<>(recipeQueryHandler.getRecipeById(id)));
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<RecipeResponse>>> searchRecipes(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String dietaryTag) {

        if (title != null && !title.isBlank()) {
            return ResponseEntity.ok(new ApiResponse<>(recipeQueryHandler.searchRecipesByTitle(title)));
        } else if (dietaryTag != null && !dietaryTag.isBlank()) {
            return ResponseEntity.ok(new ApiResponse<>(recipeQueryHandler.searchRecipesByDietaryTag(dietaryTag)));
        }

        return ResponseEntity.ok(new ApiResponse<>(recipeQueryHandler.getAllRecipes()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteRecipe(@PathVariable Long id, Principal principal) {
        recipeCommandHandler.deleteRecipe(id, principal.getName());
        return ResponseEntity.ok(new ApiResponse<>("Recipe deleted successfully"));
    }
}