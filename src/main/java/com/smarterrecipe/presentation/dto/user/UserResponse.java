package com.smarterrecipe.presentation.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import lombok.Builder;

@Getter @Setter
@AllArgsConstructor
@Builder
public class UserResponse {
    private Long id;
    private String name;
    private String username;
    private String email;
    private String bio;
    private String profileImageUrl;
    private Integer followersCount;
    private Integer followingCount;
    private DietaryPreferencesResponse dietaryPreferences;

    @Getter @Setter
    public static class DietaryPreferencesResponse {
        private Boolean isVegan;
        private Boolean isVegetarian;
        private Boolean isHalal;
        private Boolean isGlutenFree;
        private Boolean isNutAllergy;
        private Boolean isDairyFree;
    }
}