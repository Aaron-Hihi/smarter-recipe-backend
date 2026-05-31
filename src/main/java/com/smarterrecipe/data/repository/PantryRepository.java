package com.smarterrecipe.data.repository;

import com.smarterrecipe.data.entity.Pantry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PantryRepository extends JpaRepository<Pantry, Long> {
    Optional<Pantry> findByNameIgnoreCase(String name);
    boolean existsByNameIgnoreCase(String name);
}