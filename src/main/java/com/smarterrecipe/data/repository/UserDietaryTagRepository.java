package com.smarterrecipe.data.repository;

import com.smarterrecipe.data.entity.UserDietaryTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserDietaryTagRepository extends JpaRepository<UserDietaryTag, Long> {
    List<UserDietaryTag> findAllByUserId(Long userId);
    boolean existsByUserIdAndDietaryTagId(Long userId, Long dietaryTagId);
    Optional<UserDietaryTag> findByUserIdAndDietaryTagId(Long userId, Long dietaryTagId);
}
