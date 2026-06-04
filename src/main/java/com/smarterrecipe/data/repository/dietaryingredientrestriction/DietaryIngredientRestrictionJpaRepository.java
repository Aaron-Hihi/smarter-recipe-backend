package com.smarterrecipe.data.repository.dietaryingredientrestriction;

import com.smarterrecipe.data.entity.DietaryIngredientRestriction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DietaryIngredientRestrictionJpaRepository extends JpaRepository<DietaryIngredientRestriction, Long> {
    @Query("SELECT d FROM DietaryIngredientRestriction d WHERE d.dietaryTag.id IN :tagIds AND d.ingredient.id IN :ingredientIds")
    List<DietaryIngredientRestriction> findViolations(
            @Param("tagIds") List<Long> tagIds,
            @Param("ingredientIds") List<Long> ingredientIds
    );
}