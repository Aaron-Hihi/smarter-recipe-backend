package com.smarterrecipe.application.handler;

import com.smarterrecipe.data.entity.DietaryTag;
import com.smarterrecipe.data.entity.UserDietaryTag;
import com.smarterrecipe.data.repository.dietarytag.DietaryTagJpaRepository;
import com.smarterrecipe.data.repository.user.UserDietaryTagJpaRepository;
import com.smarterrecipe.data.repository.user.UserJpaRepository;
import com.smarterrecipe.domain.service.UserService;
import com.smarterrecipe.presentation.dto.DietaryPreferencesRequest;
import com.smarterrecipe.presentation.dto.user.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserHandler {

    private final UserService userService;
    private final UserJpaRepository userJpaRepository;
    private final UserDietaryTagJpaRepository userDietaryTagJpaRepository;
    private final DietaryTagJpaRepository dietaryTagJpaRepository;

    public List<UserResponse> getAllUsers() {
        return userService.getAllUsers().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public UserResponse getProfile(String username) {
        return toResponse(userService.getByUsername(username));
    }

    private UserResponse toResponse(com.smarterrecipe.domain.model.UserModel user) {
        UserResponse.DietaryPreferencesResponse dietary = new UserResponse.DietaryPreferencesResponse();
        
        List<UserDietaryTag> userTags = userDietaryTagJpaRepository.findByUserId(user.getId());
        List<String> activeTags = userTags.stream().map(ut -> ut.getDietaryTag().getName().toLowerCase()).collect(Collectors.toList());
        
        dietary.setIsVegan(activeTags.contains("vegan"));
        dietary.setIsVegetarian(activeTags.contains("vegetarian"));
        dietary.setIsHalal(activeTags.contains("halal"));
        dietary.setIsGlutenFree(activeTags.contains("gluten-free"));
        dietary.setIsNutAllergy(activeTags.contains("nut-allergy"));
        dietary.setIsDairyFree(activeTags.contains("dairy-free"));

        return UserResponse.builder()
                .id(user.getId())
                .name(user.getFullName() != null ? user.getFullName() : user.getUsername())
                .username(user.getUsername())
                .email(user.getEmail())
                .bio(user.getBio() != null ? user.getBio() : "")
                .profileImageUrl(user.getProfilePictureUrl())
                .followersCount(0)
                .followingCount(0)
                .dietaryPreferences(dietary)
                .build();
    }

    @Transactional
    public UserResponse updateProfile(String username, String name, String bio) {
        com.smarterrecipe.domain.model.UserModel user = userService.getByUsername(username);
        
        com.smarterrecipe.domain.model.UserModel updated = com.smarterrecipe.domain.model.UserModel.builder()
                .id(user.getId())
                .username(user.getUsername())
                .fullName(name)
                .bio(bio)
                .email(user.getEmail())
                .password(user.getPassword())
                .role(user.getRole())
                .profilePictureUrl(user.getProfilePictureUrl())
                .createdAt(user.getCreatedAt())
                .build();
                
        return toResponse(userService.updateUser(updated));
    }

    @Transactional
    public UserResponse updateDietaryPreferences(String username, DietaryPreferencesRequest prefs) {
        com.smarterrecipe.domain.model.UserModel userModel = userService.getByUsername(username);
        com.smarterrecipe.data.entity.User userEntity = userJpaRepository.findById(userModel.getId()).orElseThrow();
        
        userDietaryTagJpaRepository.deleteByUserId(userEntity.getId());
        
        addDietaryTagIfTrue(userEntity, prefs.getIsVegan(), "Vegan");
        addDietaryTagIfTrue(userEntity, prefs.getIsVegetarian(), "Vegetarian");
        addDietaryTagIfTrue(userEntity, prefs.getIsHalal(), "Halal");
        addDietaryTagIfTrue(userEntity, prefs.getIsGlutenFree(), "Gluten-Free");
        addDietaryTagIfTrue(userEntity, prefs.getIsNutAllergy(), "Nut-Allergy");
        addDietaryTagIfTrue(userEntity, prefs.getIsDairyFree(), "Dairy-Free");
        
        return toResponse(userModel);
    }
    
    private void addDietaryTagIfTrue(com.smarterrecipe.data.entity.User user, Boolean isTrue, String tagName) {
        if (Boolean.TRUE.equals(isTrue)) {
            DietaryTag tag = dietaryTagJpaRepository.findByNameIgnoreCase(tagName).orElseGet(() -> {
                DietaryTag newTag = new DietaryTag();
                newTag.setName(tagName);
                return dietaryTagJpaRepository.save(newTag);
            });
            
            UserDietaryTag userTag = new UserDietaryTag();
            userTag.setUser(user);
            userTag.setDietaryTag(tag);
            userDietaryTagJpaRepository.save(userTag);
        }
    }
}