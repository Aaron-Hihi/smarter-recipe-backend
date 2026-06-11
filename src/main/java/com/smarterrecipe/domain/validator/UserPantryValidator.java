package com.smarterrecipe.domain.validator;

import com.smarterrecipe.data.repository.pantry.PantryJpaRepository;
import com.smarterrecipe.data.repository.pantry.UserPantryJpaRepository;
import com.smarterrecipe.data.repository.user.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserPantryValidator {

    private final UserJpaRepository userRepository;
    private final com.smarterrecipe.data.repository.ingredient.IngredientJpaRepository ingredientRepository;
    private final UserPantryJpaRepository userPantryRepository;

    public void validateUserExists(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new IllegalArgumentException("User tidak ditemukan");
        }
    }

    public void validateIngredientExists(Long ingredientId) {
        if (!ingredientRepository.existsById(ingredientId)) {
            throw new IllegalArgumentException("Ingredient tidak ditemukan");
        }
    }

    public void validateNotDuplicate(Long userId, Long ingredientId) {
        if (userPantryRepository.existsByUserIdAndIngredientId(userId, ingredientId)) {
            throw new IllegalArgumentException("User sudah memiliki ingredient ini di pantry");
        }
    }

    public void validateUserPantryExists(Long userId, Long ingredientId) {
        if (!userPantryRepository.existsByUserIdAndIngredientId(userId, ingredientId)) {
            throw new IllegalArgumentException("Ingredient tidak ditemukan pada pantry user ini");
        }
    }
}
