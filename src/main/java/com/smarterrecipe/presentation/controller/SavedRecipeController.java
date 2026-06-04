package com.smarterrecipe.presentation.controller;

import com.smarterrecipe.application.handler.RecipeQueryHandler;
import com.smarterrecipe.application.handler.SavedRecipeHandler;
import com.smarterrecipe.presentation.dto.ApiResponse;
import com.smarterrecipe.presentation.dto.GenericMessageResponse;
import com.smarterrecipe.presentation.dto.RecipeResponse;
import com.smarterrecipe.presentation.dto.SavedRecipeRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/saved-recipes")
@RequiredArgsConstructor
public class SavedRecipeController {

    private final SavedRecipeHandler savedRecipeHandler;

    @GetMapping
    public ResponseEntity<ApiResponse<List<RecipeResponse>>> getSavedRecipes(Principal principal) {
        return ResponseEntity.ok(new ApiResponse<>(savedRecipeHandler.getSavedRecipes(principal.getName())));
    }

    @PostMapping
    public ResponseEntity<GenericMessageResponse> saveRecipe(@RequestBody SavedRecipeRequest request, Principal principal) {
        savedRecipeHandler.saveRecipe(principal.getName(), request.getRecipeId());
        return ResponseEntity.ok(new GenericMessageResponse("Recipe saved"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GenericMessageResponse> removeSavedRecipe(@PathVariable Long id, Principal principal) {
        savedRecipeHandler.removeSavedRecipe(principal.getName(), id);
        return ResponseEntity.ok(new GenericMessageResponse("Recipe removed from saved"));
    }
}
