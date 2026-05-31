package com.smarterrecipe.data.repository;

import com.smarterrecipe.data.entity.DietaryIngredientRestriction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DietaryIngredientRestrictionRepository extends JpaRepository<DietaryIngredientRestriction, Long> {
    boolean existsByDietaryTagIdAndIngredientId(Long dietaryTagId, Long ingredientId);
}