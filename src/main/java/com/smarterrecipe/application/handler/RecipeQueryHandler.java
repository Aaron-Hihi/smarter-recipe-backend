package com.smarterrecipe.application.handler;

import com.smarterrecipe.domain.model.RecipeModel;
import com.smarterrecipe.domain.service.RecipeService;
import com.smarterrecipe.presentation.dto.RecipeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecipeQueryHandler {

    private final RecipeService recipeService;

    public List<RecipeResponse> getAllRecipes() {
        return mapToResponseList(recipeService.getAllRecipes());
    }

    public RecipeResponse getRecipeById(Long id) {
        return mapToResponse(recipeService.getRecipeById(id));
    }

    public List<RecipeResponse> searchRecipesByTitle(String title) {
        return mapToResponseList(recipeService.searchRecipes(title));
    }

    public List<RecipeResponse> searchRecipesByDietaryTag(String tagName) {
        return mapToResponseList(recipeService.getDietRecommendations(tagName));
    }

    private List<RecipeResponse> mapToResponseList(List<RecipeModel> models) {
        return models.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private RecipeResponse mapToResponse(RecipeModel model) {
        return RecipeResponse.builder()
                .id(model.getId())
                .title(model.getTitle())
                .authorName(model.getCreatorId() != null ? "User " + model.getCreatorId() : "Unknown") // Placeholder since we only have creatorId in RecipeModel now, we'll fix later if needed
                .authorId(model.getCreatorId())
                .cookTime(model.getPreparationTime() != null ? model.getPreparationTime() + " mins" : "N/A")
                .servings(model.getServingSize())
                .averageRating(model.getAverageRating() != null ? model.getAverageRating() : 0.0)
                .imageUrl(model.getThumbnailUrl())
                .isPublished("PUBLISHED".equals(model.getStatus() != null ? model.getStatus().name() : ""))
                .dietaryTags(model.getDietaryTags() != null ? model.getDietaryTags() : List.of())
                .steps(mapSteps(model.getSteps()))
                .ingredients(mapIngredients(model.getIngredients()))
                .build();
    }

    private List<String> mapSteps(List<RecipeModel.StepModel> steps) {
        if (steps == null) return List.of();
        return steps.stream()
                .map(RecipeModel.StepModel::getInstruction)
                .collect(Collectors.toList());
    }

    private List<RecipeResponse.IngredientResponse> mapIngredients(List<RecipeModel.IngredientModel> ingredients) {
        if (ingredients == null) return List.of();
        return ingredients.stream()
                .map(ing -> RecipeResponse.IngredientResponse.builder()
                        .id(ing.getIngredientId())
                        .name(ing.getIngredientName())
                        .quantity(ing.getAmount() + " " + ing.getUnit())
                        .isMissing(false)
                        .build())
                .collect(Collectors.toList());
    }
}