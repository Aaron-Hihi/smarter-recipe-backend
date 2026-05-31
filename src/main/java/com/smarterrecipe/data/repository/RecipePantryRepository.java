package com.smarterrecipe.data.repository;

import com.smarterrecipe.data.entity.RecipePantry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipePantryRepository extends JpaRepository<RecipePantry, Long> {}