package com.smarterrecipe.data.repository.pantry;

import com.smarterrecipe.data.entity.UserPantry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserPantryJpaRepository extends JpaRepository<UserPantry, Long> {
    List<UserPantry> findAllByUserId(Long userId);
    boolean existsByUserIdAndIngredientId(Long userId, Long ingredientId);
    Optional<UserPantry> findByUserIdAndIngredientId(Long userId, Long ingredientId);
}
