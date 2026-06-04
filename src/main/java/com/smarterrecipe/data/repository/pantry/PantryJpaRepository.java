package com.smarterrecipe.data.repository.pantry;

import com.smarterrecipe.data.entity.Pantry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PantryJpaRepository extends JpaRepository<Pantry, Long> {
    boolean existsByNameIgnoreCase(String name);
}