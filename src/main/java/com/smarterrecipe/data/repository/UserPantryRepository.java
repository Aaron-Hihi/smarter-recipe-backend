package com.smarterrecipe.data.repository;

import com.smarterrecipe.data.entity.UserPantry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserPantryRepository extends JpaRepository<UserPantry, Long> {
    List<UserPantry> findAllByUserId(Long userId);
    boolean existsByUserIdAndPantryId(Long userId, Long pantryId);
    Optional<UserPantry> findByUserIdAndPantryId(Long userId, Long pantryId);
}
