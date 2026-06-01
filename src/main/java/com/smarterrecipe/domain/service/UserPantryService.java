package com.smarterrecipe.domain.service;

import com.smarterrecipe.data.entity.Pantry;
import com.smarterrecipe.data.entity.User;
import com.smarterrecipe.data.entity.UserPantry;
import com.smarterrecipe.data.repository.PantryRepository;
import com.smarterrecipe.data.repository.UserPantryRepository;
import com.smarterrecipe.data.repository.UserRepository;
import com.smarterrecipe.domain.validator.UserPantryValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserPantryService {

    private final UserPantryRepository userPantryRepository;
    private final UserRepository userRepository;
    private final PantryRepository pantryRepository;
    private final UserPantryValidator userPantryValidator;

    public List<UserPantry> getUserPantries(Long userId) {
        userPantryValidator.validateUserExists(userId);
        return userPantryRepository.findAllByUserId(userId);
    }

    @Transactional
    public UserPantry addPantryToUser(Long userId, Long pantryId) {
        userPantryValidator.validateUserExists(userId);
        userPantryValidator.validatePantryExists(pantryId);
        userPantryValidator.validateNotDuplicate(userId, pantryId);

        User user = userRepository.findById(userId).orElseThrow();
        Pantry pantry = pantryRepository.findById(pantryId).orElseThrow();

        UserPantry userPantry = new UserPantry();
        userPantry.setUser(user);
        userPantry.setPantry(pantry);

        return userPantryRepository.save(userPantry);
    }

    @Transactional
    public void removePantryFromUser(Long userId, Long pantryId) {
        userPantryValidator.validateUserExists(userId);
        userPantryValidator.validateUserPantryExists(userId, pantryId);

        UserPantry userPantry = userPantryRepository.findByUserIdAndPantryId(userId, pantryId).orElseThrow();
        userPantryRepository.delete(userPantry);
    }
}
