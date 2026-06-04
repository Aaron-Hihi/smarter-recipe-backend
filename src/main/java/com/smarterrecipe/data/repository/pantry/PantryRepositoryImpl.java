package com.smarterrecipe.data.repository.pantry;

import com.smarterrecipe.data.entity.Pantry;
import com.smarterrecipe.domain.model.PantryModel;
import com.smarterrecipe.domain.repository.PantryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PantryRepositoryImpl implements PantryRepository {
    private final PantryJpaRepository jpaRepository;

    @Override
    public boolean existsByName(String name) {
        return jpaRepository.existsByNameIgnoreCase(name);
    }

    @Override
    public PantryModel save(PantryModel model) {
        Pantry entity = new Pantry();
        entity.setName(model.getName());
        entity.setDescription(model.getDescription());
        entity = jpaRepository.save(entity);
        return toModel(entity);
    }

    @Override
    public List<PantryModel> getAll() {
        return jpaRepository.findAll().stream().map(this::toModel).collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        jpaRepository.deleteById(id);
    }

    private PantryModel toModel(Pantry entity) {
        return PantryModel.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .build();
    }
}