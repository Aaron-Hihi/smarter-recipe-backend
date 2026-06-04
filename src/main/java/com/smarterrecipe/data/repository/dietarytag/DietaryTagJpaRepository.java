package com.smarterrecipe.data.repository.dietarytag;

import com.smarterrecipe.data.entity.DietaryTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DietaryTagJpaRepository extends JpaRepository<DietaryTag, Long> {
    Optional<DietaryTag> findByNameIgnoreCase(String name);
    boolean existsByNameIgnoreCase(String name);
}