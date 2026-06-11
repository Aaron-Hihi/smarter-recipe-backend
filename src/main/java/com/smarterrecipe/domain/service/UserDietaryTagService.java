package com.smarterrecipe.domain.service;

import com.smarterrecipe.data.entity.DietaryTag;
import com.smarterrecipe.data.entity.User;
import com.smarterrecipe.data.entity.UserDietaryTag;
import com.smarterrecipe.data.repository.DietaryTagRepository;
import com.smarterrecipe.data.repository.UserDietaryTagRepository;
import com.smarterrecipe.data.repository.UserRepository;
import com.smarterrecipe.domain.validator.UserDietaryTagValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserDietaryTagService {

    private final UserDietaryTagRepository userDietaryTagRepository;
    private final UserRepository userRepository;
    private final DietaryTagRepository dietaryTagRepository;
    private final UserDietaryTagValidator userDietaryTagValidator;

    public List<UserDietaryTag> getUserDietaryTags(Long userId) {
        userDietaryTagValidator.validateUserExists(userId);
        return userDietaryTagRepository.findAllByUserId(userId);
    }

    @Transactional
    public UserDietaryTag addDietaryTagToUser(Long userId, Long dietaryTagId) {
        userDietaryTagValidator.validateUserExists(userId);
        userDietaryTagValidator.validateDietaryTagExists(dietaryTagId);
        userDietaryTagValidator.validateNotDuplicate(userId, dietaryTagId);

        User user = userRepository.findById(userId).orElseThrow();
        DietaryTag dietaryTag = dietaryTagRepository.findById(dietaryTagId).orElseThrow();

        UserDietaryTag userDietaryTag = new UserDietaryTag();
        userDietaryTag.setUser(user);
        userDietaryTag.setDietaryTag(dietaryTag);

        return userDietaryTagRepository.save(userDietaryTag);
    }

    @Transactional
    public void removeDietaryTagFromUser(Long userId, Long dietaryTagId) {
        userDietaryTagValidator.validateUserExists(userId);
        userDietaryTagValidator.validateUserDietaryTagExists(userId, dietaryTagId);

        UserDietaryTag userDietaryTag = userDietaryTagRepository.findByUserIdAndDietaryTagId(userId, dietaryTagId).orElseThrow();
        userDietaryTagRepository.delete(userDietaryTag);
    }
}
