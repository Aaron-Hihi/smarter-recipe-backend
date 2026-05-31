package com.smarterrecipe.data.repository;

import com.smarterrecipe.data.entity.Recipe;
import com.smarterrecipe.domain.model.enums.RecipeStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    List<Recipe> findByStatus(RecipeStatus status);
}