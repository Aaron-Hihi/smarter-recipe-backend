package com.smarterrecipe.data.repository.recipe;

import com.smarterrecipe.data.entity.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavoriteJpaRepository extends JpaRepository<Favorite, Long> {
    List<Favorite> findByUserId(Long userId);
    Optional<Favorite> findByUserIdAndRecipeId(Long userId, Long recipeId);
    void deleteByUserIdAndRecipeId(Long userId, Long recipeId);
}
