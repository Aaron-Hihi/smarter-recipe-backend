package com.smarterrecipe.application.handler;

import com.smarterrecipe.domain.engine.DietaryRuleEngine;
import com.smarterrecipe.domain.model.RecipeModel;
import com.smarterrecipe.domain.model.enums.RecipeStatus;
import com.smarterrecipe.domain.service.RecipeService;
import com.smarterrecipe.domain.model.UserModel;
import com.smarterrecipe.domain.service.IngredientService;
import com.smarterrecipe.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecipeCommandHandler {

    private final RecipeService recipeService;
    private final DietaryRuleEngine dietaryRuleEngine;

    private final IngredientService ingredientService;
    private final UserService userService;

    public RecipeModel createRecipe(String title, List<String> ingredients, String creatorUsername) {
        UserModel creator = userService.getByUsername(creatorUsername);
        
        List<RecipeModel.IngredientModel> ingredientModels = new ArrayList<>();
        
        for (String ingredientName : ingredients) {
            com.smarterrecipe.domain.model.IngredientModel ingredient = ingredientService.findOrCreateByName(ingredientName);
            
            ingredientModels.add(RecipeModel.IngredientModel.builder()
                    .ingredientName(ingredient.getName())
                    .ingredientId(ingredient.getId())
                    .amount(1.0)
                    .unit("pcs")
                    .build());
        }

        RecipeModel newModel = RecipeModel.builder()
                .title(title)
                .description("A delicious recipe.")
                .creatorId(creator.getId())
                .preparationTime(30)
                .servingSize(2)
                .status(RecipeStatus.PUBLISHED)
                .steps(List.of(RecipeModel.StepModel.builder().stepNumber(1).instruction("Mix ingredients and cook.").build()))
                .ingredients(ingredientModels)
                .build();

        return recipeService.createRecipe(newModel);
    }
}