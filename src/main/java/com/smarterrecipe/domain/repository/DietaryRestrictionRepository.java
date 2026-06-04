package com.smarterrecipe.domain.repository;

import java.util.List;

public interface DietaryRestrictionRepository {
    List<Long> findViolatingIngredients(List<Long> tagIds, List<Long> ingredientIds);
}