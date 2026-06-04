package com.smarterrecipe.domain.repository;

import com.smarterrecipe.domain.model.IngredientModel;
import java.util.List;

public interface IngredientRepository {
    boolean existsByName(String name);
    IngredientModel save(IngredientModel model);
    List<IngredientModel> getAll();
    java.util.Optional<IngredientModel> findByName(String name);
}