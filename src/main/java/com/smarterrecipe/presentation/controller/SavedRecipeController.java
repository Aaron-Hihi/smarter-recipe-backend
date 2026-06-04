package com.smarterrecipe.presentation.controller;

import com.smarterrecipe.application.handler.RecipeQueryHandler;
import com.smarterrecipe.presentation.dto.ApiResponse;
import com.smarterrecipe.presentation.dto.GenericMessageResponse;
import com.smarterrecipe.presentation.dto.RecipeResponse;
import com.smarterrecipe.presentation.dto.SavedRecipeRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/saved-recipes")
@RequiredArgsConstructor
public class SavedRecipeController {

    private final RecipeQueryHandler recipeQueryHandler;

    @GetMapping
    public ResponseEntity<ApiResponse<List<RecipeResponse>>> getSavedRecipes() {
        // Stub: returning all recipes instead of saved for MVP
        return ResponseEntity.ok(new ApiResponse<>(recipeQueryHandler.getAllRecipes()));
    }

    @PostMapping
    public ResponseEntity<GenericMessageResponse> saveRecipe(@RequestBody SavedRecipeRequest request) {
        return ResponseEntity.ok(new GenericMessageResponse("Recipe saved"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GenericMessageResponse> removeSavedRecipe(@PathVariable Long id) {
        return ResponseEntity.ok(new GenericMessageResponse("Recipe removed from saved"));
    }
}
