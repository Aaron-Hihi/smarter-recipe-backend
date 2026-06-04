package com.smarterrecipe.domain.service;

import com.smarterrecipe.domain.model.RecipeModel;
import com.smarterrecipe.domain.repository.RecipeRepository;
import java.util.List;

public class RecipeService {

    private final RecipeRepository recipeRepository;

    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public RecipeModel createRecipe(RecipeModel newRecipe) {
        return recipeRepository.saveRecipe(newRecipe);
    }

    public List<RecipeModel> getAllRecipes() {
        return recipeRepository.getAllPublishedRecipes();
    }

    public RecipeModel getRecipeById(Long id) {
        return recipeRepository.getRecipeById(id);
    }

    public List<RecipeModel> searchRecipes(String keyword) {
        return recipeRepository.searchPublishedRecipes(keyword);
    }

    public List<RecipeModel> getDietRecommendations(String tagName) {
        return recipeRepository.getPublishedRecipesByTag(tagName);
    }

    public void deleteRecipe(Long id) {
        recipeRepository.deleteRecipe(id);
    }

    public boolean existsByTitle(String title) {
        return recipeRepository.existsByTitle(title);
    }
}