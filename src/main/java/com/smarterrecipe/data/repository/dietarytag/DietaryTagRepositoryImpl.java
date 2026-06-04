package com.smarterrecipe.data.repository.dietarytag;

import com.smarterrecipe.data.entity.DietaryTag;
import com.smarterrecipe.domain.model.DietaryTagModel;
import com.smarterrecipe.domain.repository.DietaryTagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class DietaryTagRepositoryImpl implements DietaryTagRepository {
    private final DietaryTagJpaRepository jpaRepository;

    @Override
    public boolean existsByName(String name) {
        return jpaRepository.existsByNameIgnoreCase(name);
    }

    @Override
    public DietaryTagModel save(DietaryTagModel model) {
        DietaryTag entity = new DietaryTag();
        entity.setName(model.getName());
        entity.setDescription(model.getDescription());
        entity = jpaRepository.save(entity);
        return toModel(entity);
    }

    @Override
    public List<DietaryTagModel> getAll() {
        return jpaRepository.findAll().stream().map(this::toModel).collect(Collectors.toList());
    }

    private DietaryTagModel toModel(DietaryTag entity) {
        return DietaryTagModel.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .build();
    }
}