package com.smarterrecipe.domain.service;

import com.smarterrecipe.domain.model.DietaryTagModel;
import com.smarterrecipe.domain.repository.DietaryTagRepository;
import java.util.List;

public class DietaryTagService {
    private final DietaryTagRepository repository;

    public DietaryTagService(DietaryTagRepository repository) {
        this.repository = repository;
    }

    public DietaryTagModel create(DietaryTagModel model) {
        if (repository.existsByName(model.getName())) {
            throw new IllegalArgumentException("Dietary Tag already exists");
        }
        return repository.save(model);
    }

    public List<DietaryTagModel> getAll() {
        return repository.getAll();
    }
}