package com.smarterrecipe.domain.repository;

import com.smarterrecipe.domain.model.PantryModel;
import java.util.List;

public interface PantryRepository {
    boolean existsByName(String name);
    PantryModel save(PantryModel pantry);
    List<PantryModel> getAll();
    void delete(Long id);
}