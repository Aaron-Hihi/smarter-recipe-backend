package com.smarterrecipe.application.handler;

import com.smarterrecipe.data.entity.Ingredient;
import com.smarterrecipe.data.repository.IngredientRepository;
import com.smarterrecipe.presentation.dto.ingredient.IngredientRequest;
import com.smarterrecipe.presentation.dto.ingredient.IngredientResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IngredientHandler {

    private final IngredientRepository ingredientRepository;

    public IngredientResponse createIngredient(IngredientRequest request) {
        if (ingredientRepository.existsByNameIgnoreCase(request.getName())) {
            throw new IllegalArgumentException("Ingredient sudah ada");
        }

        Ingredient ingredient = new Ingredient();
        ingredient.setName(request.getName());
        ingredient = ingredientRepository.save(ingredient);

        return new IngredientResponse(ingredient.getId(), ingredient.getName());
    }

    public List<IngredientResponse> getAllIngredients() {
        return ingredientRepository.findAll().stream()
                .map(ingredient -> new IngredientResponse(
                        ingredient.getId(),
                        ingredient.getName()
                ))
                .collect(Collectors.toList());
    }
}