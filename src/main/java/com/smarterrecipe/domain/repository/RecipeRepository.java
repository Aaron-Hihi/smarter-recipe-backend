package com.smarterrecipe.domain.repository;

import com.smarterrecipe.domain.model.RecipeModel;
import java.util.List;

public interface RecipeRepository {
    List<RecipeModel> getAllPublishedRecipes();
    RecipeModel getRecipeById(Long id);
    List<RecipeModel> searchPublishedRecipes(String keyword);
    List<RecipeModel> getPublishedRecipesByTag(String tagName);
    RecipeModel saveRecipe(RecipeModel recipeModel);
    void deleteRecipe(Long id);
    boolean existsByTitle(String title);
}