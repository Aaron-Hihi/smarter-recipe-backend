package com.smarterrecipe.data.repository;

import com.smarterrecipe.data.entity.Recipe;
import com.smarterrecipe.domain.model.enums.RecipeStatus;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    @EntityGraph(value = "Recipe.deepFetch")
    List<Recipe> findByStatus(RecipeStatus status);

    @EntityGraph(value = "Recipe.deepFetch")
    List<Recipe> findByTitleContainingIgnoreCaseAndStatus(String title, RecipeStatus status);

    @EntityGraph(value = "Recipe.deepFetch")
    @Query("SELECT r FROM Recipe r JOIN r.recipeDietaryTags rdt JOIN rdt.dietaryTag dt WHERE dt.name = :tagName AND r.status = 'PUBLISHED'")
    List<Recipe> findApprovedRecipesByDietaryTag(@Param("tagName") String tagName);
}