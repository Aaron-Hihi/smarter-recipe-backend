package com.smarterrecipe.application.handler;

import com.smarterrecipe.domain.model.IngredientModel;
import com.smarterrecipe.domain.service.IngredientService;
import com.smarterrecipe.presentation.dto.ingredient.IngredientRequest;
import com.smarterrecipe.presentation.dto.ingredient.IngredientResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IngredientHandler {

    private final IngredientService ingredientService;

    public IngredientResponse createIngredient(IngredientRequest request) {
        IngredientModel model = IngredientModel.builder()
                .name(request.getName())
                .build();

        IngredientModel saved = ingredientService.create(model);
        return new IngredientResponse(saved.getId(), saved.getName());
    }

    public List<IngredientResponse> getAllIngredients() {
        return ingredientService.getAll().stream()
                .map(ing -> new IngredientResponse(ing.getId(), ing.getName()))
                .collect(Collectors.toList());
    }
}