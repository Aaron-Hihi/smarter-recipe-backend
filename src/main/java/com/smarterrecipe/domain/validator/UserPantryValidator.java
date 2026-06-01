package com.smarterrecipe.domain.validator;

import com.smarterrecipe.data.repository.PantryRepository;
import com.smarterrecipe.data.repository.UserPantryRepository;
import com.smarterrecipe.data.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserPantryValidator {

    private final UserRepository userRepository;
    private final PantryRepository pantryRepository;
    private final UserPantryRepository userPantryRepository;

    public void validateUserExists(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new IllegalArgumentException("User tidak ditemukan");
        }
    }

    public void validatePantryExists(Long pantryId) {
        if (!pantryRepository.existsById(pantryId)) {
            throw new IllegalArgumentException("Pantry tidak ditemukan");
        }
    }

    public void validateNotDuplicate(Long userId, Long pantryId) {
        if (userPantryRepository.existsByUserIdAndPantryId(userId, pantryId)) {
            throw new IllegalArgumentException("User sudah memiliki pantry ini");
        }
    }

    public void validateUserPantryExists(Long userId, Long pantryId) {
        if (!userPantryRepository.existsByUserIdAndPantryId(userId, pantryId)) {
            throw new IllegalArgumentException("Pantry tidak ditemukan pada user ini");
        }
    }
}
