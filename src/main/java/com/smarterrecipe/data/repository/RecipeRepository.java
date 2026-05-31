package com.smarterrecipe.data.repository;

import com.smarterrecipe.data.entity.Recipe;
import com.smarterrecipe.domain.model.enums.RecipeStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    @Query("SELECT DISTINCT r FROM Recipe r " +
            "JOIN r.recipePantries rp " +
            "JOIN UserPantry up ON up.pantry.id = rp.pantry.id " +
            "WHERE up.user.id = :userId AND r.status = :status")
    List<Recipe> findSuggestionsByUserId(@Param("userId") Long userId, @Param("status") RecipeStatus status);
}