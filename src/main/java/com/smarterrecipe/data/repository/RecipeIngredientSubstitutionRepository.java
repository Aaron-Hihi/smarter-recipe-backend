package com.smarterrecipe.data.repository;

import com.smarterrecipe.data.entity.RecipeIngredient;
import com.smarterrecipe.data.entity.RecipeIngredientSubstitution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeIngredientSubstitutionRepository extends JpaRepository<RecipeIngredientSubstitution, Long> {
    List<RecipeIngredientSubstitution> findByRecipeIngredient(RecipeIngredient recipeIngredient);
}