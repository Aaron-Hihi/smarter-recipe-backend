package com.smarterrecipe.domain.validator;

import com.smarterrecipe.data.repository.dietarytag.DietaryTagJpaRepository;
import com.smarterrecipe.data.repository.user.UserDietaryTagJpaRepository;
import com.smarterrecipe.data.repository.user.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserDietaryTagValidator {

    private final UserJpaRepository userRepository;
    private final DietaryTagJpaRepository dietaryTagRepository;
    private final UserDietaryTagJpaRepository userDietaryTagRepository;

    public void validateUserExists(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new IllegalArgumentException("User tidak ditemukan");
        }
    }

    public void validateDietaryTagExists(Long dietaryTagId) {
        if (!dietaryTagRepository.existsById(dietaryTagId)) {
            throw new IllegalArgumentException("Dietary tag tidak ditemukan");
        }
    }

    public void validateNotDuplicate(Long userId, Long dietaryTagId) {
        if (userDietaryTagRepository.existsByUserIdAndDietaryTagId(userId, dietaryTagId)) {
            throw new IllegalArgumentException("User sudah memiliki dietary tag ini");
        }
    }

    public void validateUserDietaryTagExists(Long userId, Long dietaryTagId) {
        if (!userDietaryTagRepository.existsByUserIdAndDietaryTagId(userId, dietaryTagId)) {
            throw new IllegalArgumentException("Dietary tag tidak ditemukan pada user ini");
        }
    }
}
