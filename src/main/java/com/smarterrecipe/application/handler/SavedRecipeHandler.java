package com.smarterrecipe.application.handler;

import com.smarterrecipe.data.entity.Favorite;
import com.smarterrecipe.data.entity.Recipe;
import com.smarterrecipe.data.entity.User;
import com.smarterrecipe.data.repository.recipe.FavoriteJpaRepository;
import com.smarterrecipe.data.repository.recipe.RecipeJpaRepository;
import com.smarterrecipe.data.repository.user.UserJpaRepository;
import com.smarterrecipe.domain.service.UserService;
import com.smarterrecipe.presentation.dto.RecipeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SavedRecipeHandler {

    private final FavoriteJpaRepository favoriteJpaRepository;
    private final UserJpaRepository userJpaRepository;
    private final RecipeJpaRepository recipeJpaRepository;
    private final UserService userService;
    private final RecipeQueryHandler recipeQueryHandler;

    @Transactional(readOnly = true)
    public List<RecipeResponse> getSavedRecipes(String username) {
        User user = userJpaRepository.findByUsername(username).orElseThrow();
        List<Favorite> favorites = favoriteJpaRepository.findByUserId(user.getId());
        
        return favorites.stream()
                .map(fav -> recipeQueryHandler.getRecipeById(fav.getRecipe().getId()))
                .collect(Collectors.toList());
    }

    @Transactional
    public void saveRecipe(String username, Long recipeId) {
        User user = userJpaRepository.findByUsername(username).orElseThrow();
        Recipe recipe = recipeJpaRepository.findById(recipeId)
                .orElseThrow(() -> new IllegalArgumentException("Recipe not found"));
        
        if (favoriteJpaRepository.findByUserIdAndRecipeId(user.getId(), recipeId).isEmpty()) {
            Favorite favorite = new Favorite();
            favorite.setUser(user);
            favorite.setRecipe(recipe);
            favoriteJpaRepository.save(favorite);
        }
    }

    @Transactional
    public void removeSavedRecipe(String username, Long recipeId) {
        User user = userJpaRepository.findByUsername(username).orElseThrow();
        favoriteJpaRepository.deleteByUserIdAndRecipeId(user.getId(), recipeId);
    }
}
