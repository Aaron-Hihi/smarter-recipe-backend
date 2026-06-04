package com.smarterrecipe.data.repository.recipe;

import com.smarterrecipe.data.entity.Recipe;
import com.smarterrecipe.data.entity.RecipeStep;
import com.smarterrecipe.data.entity.RecipeIngredient;
import com.smarterrecipe.data.entity.Ingredient;
import com.smarterrecipe.data.entity.Equipment;
import com.smarterrecipe.data.repository.ingredient.IngredientJpaRepository;
import com.smarterrecipe.data.repository.ingredient.EquipmentJpaRepository;
import com.smarterrecipe.data.repository.user.UserJpaRepository;
import com.smarterrecipe.domain.model.RecipeModel;
import com.smarterrecipe.domain.model.enums.RecipeStatus;
import com.smarterrecipe.domain.repository.RecipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.cache.annotation.Cacheable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class RecipeRepositoryImpl implements RecipeRepository {

    private final RecipeJpaRepository jpaRepository;
    private final IngredientJpaRepository ingredientJpaRepository;
    private final UserJpaRepository userJpaRepository;
    private final EquipmentJpaRepository equipmentJpaRepository;

    @Override
    @Cacheable(value = "recipes", key = "'allPublished'")
    public List<RecipeModel> getAllPublishedRecipes() {
        List<Recipe> entities = jpaRepository.findByStatus(RecipeStatus.PUBLISHED);
        return mapToModelList(entities);
    }

    @Override
    @Cacheable(value = "recipes", key = "#id")
    public RecipeModel getRecipeById(Long id) {
        Recipe entity = jpaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Recipe not found"));
        return mapToModel(entity);
    }

    @Override
    public List<RecipeModel> searchPublishedRecipes(String keyword) {
        List<Recipe> entities = jpaRepository.findByTitleContainingIgnoreCaseAndStatus(keyword, RecipeStatus.PUBLISHED);
        return mapToModelList(entities);
    }

    @Override
    @Cacheable(value = "recipesByTag", key = "#tagName")
    public List<RecipeModel> getPublishedRecipesByTag(String tagName) {
        List<Recipe> entities = jpaRepository.findApprovedRecipesByDietaryTag(tagName, RecipeStatus.PUBLISHED);
        return mapToModelList(entities);
    }

    @Override
    public RecipeModel saveRecipe(RecipeModel model) {
        Recipe recipe = model.getId() != null ? jpaRepository.findById(model.getId()).orElse(new Recipe()) : new Recipe();

        recipe.setTitle(model.getTitle());
        recipe.setDescription(model.getDescription());
        recipe.setPreparationTime(model.getPreparationTime());
        recipe.setServingSize(model.getServingSize());
        recipe.setStatus(model.getStatus());

        if (model.getCreatorId() != null) {
            recipe.setCreator(userJpaRepository.findById(model.getCreatorId()).orElseThrow(() -> new IllegalArgumentException("Creator not found")));
        }

        if (recipe.getCreatedAt() == null) {
            recipe.setCreatedAt(LocalDateTime.now());
        }

        if (model.getSteps() != null) {
            Set<RecipeStep> steps = model.getSteps().stream()
                    .map(s -> {
                        RecipeStep step = new RecipeStep();
                        step.setStepNumber(s.getStepNumber());
                        step.setInstruction(s.getInstruction());
                        step.setRecipe(recipe);
                        return step;
                    }).collect(Collectors.toSet());
            recipe.setRecipeSteps(steps);
        }

        if (model.getIngredients() != null) {
            Set<RecipeIngredient> ingredients = model.getIngredients().stream()
                    .map(i -> {
                        RecipeIngredient ri = new RecipeIngredient();
                        ri.setAmount(i.getAmount());
                        ri.setUnit(i.getUnit());
                        ri.setRecipe(recipe);

                        Ingredient ing = ingredientJpaRepository.findById(i.getIngredientId())
                                .orElseThrow(() -> new IllegalArgumentException("Ingredient not found"));
                        ri.setIngredient(ing);
                        return ri;
                    }).collect(Collectors.toSet());
            recipe.setRecipeIngredients(ingredients);
        }

        if (model.getTools() != null) {
            Set<com.smarterrecipe.data.entity.RecipeEquipment> tools = model.getTools().stream()
                    .map(t -> {
                        com.smarterrecipe.data.entity.RecipeEquipment re = new com.smarterrecipe.data.entity.RecipeEquipment();
                        re.setRecipe(recipe);

                        Equipment eq = equipmentJpaRepository.findByNameIgnoreCase(t).orElseGet(() -> {
                            Equipment newEq = new Equipment();
                            newEq.setName(t);
                            return equipmentJpaRepository.save(newEq);
                        });
                        re.setEquipment(eq);
                        return re;
                    }).collect(Collectors.toSet());
            recipe.setRecipeEquipments(tools);
        }

        Recipe savedEntity = jpaRepository.save(recipe);
        return mapToModel(savedEntity);
    }

    @Override
    public void deleteRecipe(Long id) {
        jpaRepository.deleteById(id);
    }

    @Override
    public boolean existsByTitle(String title) {
        return jpaRepository.existsByTitleIgnoreCase(title);
    }

    private List<RecipeModel> mapToModelList(List<Recipe> entities) {
        return entities.stream().map(this::mapToModel).collect(Collectors.toList());
    }

    private RecipeModel mapToModel(Recipe entity) {
        List<RecipeModel.StepModel> stepModels = entity.getRecipeSteps() == null ? List.of() : entity.getRecipeSteps().stream()
                .map(s -> RecipeModel.StepModel.builder()
                        .stepNumber(s.getStepNumber())
                        .instruction(s.getInstruction())
                        .build())
                .collect(Collectors.toList());

        List<RecipeModel.IngredientModel> ingredientModels = entity.getRecipeIngredients() == null ? List.of() : entity.getRecipeIngredients().stream()
                .map(ri -> RecipeModel.IngredientModel.builder()
                        .ingredientId(ri.getIngredient().getId())
                        .ingredientName(ri.getIngredient().getName())
                        .amount(ri.getAmount())
                        .unit(ri.getUnit())
                        .substituteIngredientIds(ri.getSubstitutions() == null ? List.of() : ri.getSubstitutions().stream()
                                .map(sub -> sub.getSubstituteIngredient().getId())
                                .collect(Collectors.toList()))
                        .build())
                .collect(Collectors.toList());

        List<String> dietaryTags = entity.getRecipeDietaryTags() == null ? List.of() : entity.getRecipeDietaryTags().stream()
                .map(t -> t.getDietaryTag().getName())
                .collect(Collectors.toList());

        List<String> tools = entity.getRecipeEquipments() == null ? List.of() : entity.getRecipeEquipments().stream()
                .map(re -> re.getEquipment().getName())
                .collect(Collectors.toList());

        return RecipeModel.builder()
                .id(entity.getId())
                .creatorId(entity.getCreator() != null ? entity.getCreator().getId() : null)
                .title(entity.getTitle())
                .thumbnailUrl(entity.getThumbnailUrl())
                .description(entity.getDescription())
                .preparationTime(entity.getPreparationTime())
                .servingSize(entity.getServingSize())
                .averageRating(entity.getAverageRating())
                .totalReviews(entity.getTotalReviews())
                .status(entity.getStatus())
                .createdAt(entity.getCreatedAt())
                .dietaryTags(dietaryTags)
                .steps(stepModels)
                .ingredients(ingredientModels)
                .tools(tools)
                .build();
    }
}