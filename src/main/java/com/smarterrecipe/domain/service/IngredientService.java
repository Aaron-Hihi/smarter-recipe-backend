package com.smarterrecipe.domain.service;

import com.smarterrecipe.domain.model.IngredientModel;
import com.smarterrecipe.domain.repository.IngredientRepository;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class IngredientService {
    private final IngredientRepository repository;

    public IngredientService(IngredientRepository repository) {
        this.repository = repository;
    }

    public IngredientModel create(IngredientModel model) {
        if (repository.existsByName(model.getName())) {
            throw new IllegalArgumentException("Ingredient sudah ada");
        }
        return repository.save(model);
    }

    public List<IngredientModel> getAll() {
        return repository.getAll();
    }

    public IngredientModel findOrCreateByName(String name) {
        return repository.findByName(name).orElseGet(() -> {
            IngredientModel newIngredient = IngredientModel.builder().name(name).build();
            return repository.save(newIngredient);
        });
    }
}