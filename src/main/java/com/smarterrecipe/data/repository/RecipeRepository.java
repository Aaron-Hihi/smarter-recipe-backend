package com.smarterrecipe.data.repository;

import com.smarterrecipe.data.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    List<Recipe> findByTitleContainingIgnoreCaseAndStatus(String keyword, String status);

    @Query("SELECT r FROM Recipe r " +
            "JOIN r.recipeDietaryTags rdt " +
            "WHERE rdt.dietaryTag.name = :tagName AND r.status = 'APPROVED'")
    List<Recipe> findApprovedRecipesByDietaryTag(@Param("tagName") String tagName);
}