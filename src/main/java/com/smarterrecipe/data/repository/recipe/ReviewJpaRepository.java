package com.smarterrecipe.data.repository.recipe;

import com.smarterrecipe.data.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewJpaRepository extends JpaRepository<Review, Long> {
    List<Review> findByRecipeIdOrderByCreatedAtDesc(Long recipeId);
    Optional<Review> findByUserIdAndRecipeId(Long userId, Long recipeId);
    boolean existsByUserIdAndRecipeId(Long userId, Long recipeId);
}
