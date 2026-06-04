package com.smarterrecipe.domain.service;

import com.smarterrecipe.domain.model.PantryModel;
import com.smarterrecipe.domain.repository.PantryRepository;
import java.util.List;

public class PantryService {
    private final PantryRepository repository;

    public PantryService(PantryRepository repository) {
        this.repository = repository;
    }

    public PantryModel create(PantryModel model) {
        if (repository.existsByName(model.getName())) {
            throw new IllegalArgumentException("Pantry already exists");
        }
        return repository.save(model);
    }

    public List<PantryModel> getAll() {
        return repository.getAll();
    }

    public void delete(Long id) {
        repository.delete(id);
    }
}