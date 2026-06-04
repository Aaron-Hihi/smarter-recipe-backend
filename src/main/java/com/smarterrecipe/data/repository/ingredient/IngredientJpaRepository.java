package com.smarterrecipe.data.repository.ingredient;

import com.smarterrecipe.data.entity.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IngredientJpaRepository extends JpaRepository<Ingredient, Long> {
    Optional<Ingredient> findByNameIgnoreCase(String name);
    boolean existsByNameIgnoreCase(String name);
}