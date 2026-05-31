package com.smarterrecipe.data.repository;

import com.smarterrecipe.data.entity.RecipeStep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeStepRepository extends JpaRepository<RecipeStep, Long> {}
