package com.smarterrecipe.domain.service;

import com.smarterrecipe.data.entity.Ingredient;
import com.smarterrecipe.data.entity.User;
import com.smarterrecipe.data.entity.UserPantry;
import com.smarterrecipe.data.repository.ingredient.IngredientJpaRepository;
import com.smarterrecipe.data.repository.pantry.UserPantryJpaRepository;
import com.smarterrecipe.data.repository.user.UserJpaRepository;
import com.smarterrecipe.domain.validator.UserPantryValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserPantryService {

    private final UserPantryJpaRepository userPantryRepository;
    private final UserJpaRepository userRepository;
    private final IngredientJpaRepository ingredientRepository;
    private final UserPantryValidator userPantryValidator;

    public List<UserPantry> getUserPantries(Long userId) {
        userPantryValidator.validateUserExists(userId);
        return userPantryRepository.findAllByUserId(userId);
    }

    @Transactional
    public UserPantry addIngredientToUserPantry(Long userId, Long ingredientId, Double quantity, String unit) {
        userPantryValidator.validateUserExists(userId);
        userPantryValidator.validateIngredientExists(ingredientId);

        // If it exists, we could either update or just throw error as before.
        userPantryValidator.validateNotDuplicate(userId, ingredientId);

        User user = userRepository.findById(userId).orElseThrow();
        Ingredient ingredient = ingredientRepository.findById(ingredientId).orElseThrow();

        UserPantry userPantry = new UserPantry();
        userPantry.setUser(user);
        userPantry.setIngredient(ingredient);
        userPantry.setQuantity(quantity != null ? quantity : 1.0);
        userPantry.setUnit(unit != null ? unit : "pcs");

        return userPantryRepository.save(userPantry);
    }

    @Transactional
    public void removeIngredientFromUserPantry(Long userId, Long ingredientId) {
        userPantryValidator.validateUserExists(userId);
        userPantryValidator.validateUserPantryExists(userId, ingredientId);

        UserPantry userPantry = userPantryRepository.findByUserIdAndIngredientId(userId, ingredientId).orElseThrow();
        userPantryRepository.delete(userPantry);
    }
}
