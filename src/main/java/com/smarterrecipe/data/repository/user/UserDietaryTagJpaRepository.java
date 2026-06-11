package com.smarterrecipe.data.repository.user;

import com.smarterrecipe.data.entity.UserDietaryTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDietaryTagJpaRepository extends JpaRepository<UserDietaryTag, Long> {
    List<UserDietaryTag> findByUserId(Long userId);
    List<UserDietaryTag> findAllByUserId(Long userId);
    void deleteByUserId(Long userId);
    java.util.Optional<UserDietaryTag> findByUserIdAndDietaryTagId(Long userId, Long dietaryTagId);
    boolean existsByUserIdAndDietaryTagId(Long userId, Long dietaryTagId);
}
