package com.smarterrecipe.presentation.controller;

import com.smarterrecipe.application.handler.RecipeCommandHandler;
import com.smarterrecipe.application.handler.RecipeQueryHandler;
import com.smarterrecipe.presentation.dto.RecipeCreateRequest;
import com.smarterrecipe.presentation.dto.RecipeResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/recipes")
@RequiredArgsConstructor
public class RecipeController {

    private final RecipeCommandHandler recipeCommandHandler;
    private final RecipeQueryHandler recipeQueryHandler;

    @PostMapping
    public ResponseEntity<String> createRecipe(@Valid @RequestBody RecipeCreateRequest request) {
        return ResponseEntity.ok(recipeCommandHandler.createRecipe(request));
    }

    @GetMapping
    public ResponseEntity<List<RecipeResponse>> getAllRecipes() {
        return ResponseEntity.ok(recipeQueryHandler.getAllRecipes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecipeResponse> getRecipeById(@PathVariable Long id) {
        return ResponseEntity.ok(recipeQueryHandler.getRecipeById(id));
    }
}