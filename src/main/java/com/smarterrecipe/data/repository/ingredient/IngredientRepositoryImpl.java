package com.smarterrecipe.data.repository.ingredient;

import com.smarterrecipe.data.entity.Ingredient;
import com.smarterrecipe.domain.model.IngredientModel;
import com.smarterrecipe.domain.repository.IngredientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class IngredientRepositoryImpl implements IngredientRepository {
    private final IngredientJpaRepository jpaRepository;

    @Override
    public boolean existsByName(String name) {
        return jpaRepository.existsByNameIgnoreCase(name);
    }

    @Override
    public IngredientModel save(IngredientModel model) {
        Ingredient entity = new Ingredient();
        entity.setName(model.getName());
        entity = jpaRepository.save(entity);
        return toModel(entity);
    }

    @Override
    public List<IngredientModel> getAll() {
        return jpaRepository.findAll().stream().map(this::toModel).collect(Collectors.toList());
    }

    @Override
    public java.util.Optional<IngredientModel> findByName(String name) {
        return jpaRepository.findByNameIgnoreCase(name).map(this::toModel);
    }

    private IngredientModel toModel(Ingredient entity) {
        return IngredientModel.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }
}