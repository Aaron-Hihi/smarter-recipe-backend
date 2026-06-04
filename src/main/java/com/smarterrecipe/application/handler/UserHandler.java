package com.smarterrecipe.application.handler;

import com.smarterrecipe.domain.service.UserService;
import com.smarterrecipe.presentation.dto.user.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserHandler {

    private final UserService userService;

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
        dietary.setIsVegan(false); // Default stub values for MVP
        dietary.setIsVegetarian(false);
        dietary.setIsHalal(true);
        dietary.setIsGlutenFree(false);
        dietary.setIsNutAllergy(false);
        dietary.setIsDairyFree(false);

        return UserResponse.builder()
                .id(user.getId())
                .name(user.getUsername()) // Mapping username to name for now
                .username(user.getUsername())
                .email(user.getEmail())
                .bio("I love cooking!") // Stub
                .profileImageUrl(user.getProfilePictureUrl())
                .followersCount(0)
                .followingCount(0)
                .dietaryPreferences(dietary)
                .build();
    }
}