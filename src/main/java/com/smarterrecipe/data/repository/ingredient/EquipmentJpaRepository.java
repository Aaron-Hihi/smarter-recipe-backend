package com.smarterrecipe.data.repository.ingredient;

import com.smarterrecipe.data.entity.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EquipmentJpaRepository extends JpaRepository<Equipment, Long> {
    Optional<Equipment> findByNameIgnoreCase(String name);
}
