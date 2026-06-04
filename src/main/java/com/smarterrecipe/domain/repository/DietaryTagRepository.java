package com.smarterrecipe.domain.repository;

import com.smarterrecipe.domain.model.DietaryTagModel;
import java.util.List;

public interface DietaryTagRepository {
    boolean existsByName(String name);
    DietaryTagModel save(DietaryTagModel model);
    List<DietaryTagModel> getAll();
}