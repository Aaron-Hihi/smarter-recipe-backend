package com.smarterrecipe.domain.service;

import com.smarterrecipe.data.entity.Recipe;
import com.smarterrecipe.data.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;

    @Autowired
    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public List<Recipe> searchRecipes(String keyword) {
        return recipeRepository.findByTitleContainingIgnoreCaseAndStatus(keyword, "APPROVED");
    }

    public List<Recipe> getDietRecommendations(String tagName) {
        return recipeRepository.findApprovedRecipesByDietaryTag(tagName);
    }
}