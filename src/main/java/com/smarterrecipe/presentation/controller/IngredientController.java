package com.smarterrecipe.presentation.controller;

import com.smarterrecipe.application.handler.IngredientHandler;
import com.smarterrecipe.presentation.dto.ingredient.IngredientRequest;
import com.smarterrecipe.presentation.dto.ingredient.IngredientResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ingredients")
@RequiredArgsConstructor
public class IngredientController {

    private final IngredientHandler ingredientHandler;

    @PostMapping
    public ResponseEntity<IngredientResponse> createIngredient(@Valid @RequestBody IngredientRequest request) {
        return ResponseEntity.ok(ingredientHandler.createIngredient(request));
    }

    @GetMapping
    public ResponseEntity<List<IngredientResponse>> getAllIngredients() {
        return ResponseEntity.ok(ingredientHandler.getAllIngredients());
    }
}