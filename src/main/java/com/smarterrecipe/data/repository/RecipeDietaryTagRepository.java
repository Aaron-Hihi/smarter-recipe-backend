package com.smarterrecipe.data.repository;

import com.smarterrecipe.data.entity.RecipeDietaryTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeDietaryTagRepository extends JpaRepository<RecipeDietaryTag, Long> {}