package com.smarterrecipe.application.handler;

import com.smarterrecipe.data.entity.User;
import com.smarterrecipe.data.entity.UserPantry;
import com.smarterrecipe.data.repository.user.UserJpaRepository;
import com.smarterrecipe.domain.service.UserPantryService;
import com.smarterrecipe.presentation.dto.userpantry.UserPantryRequest;
import com.smarterrecipe.presentation.dto.userpantry.UserPantryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserPantryHandler {

    private final UserPantryService userPantryService;
    private final UserJpaRepository userRepository;
    private final com.smarterrecipe.domain.service.IngredientService ingredientService;

    public List<UserPantryResponse> getMyPantries() {
        Long userId = getAuthenticatedUserId();
        return userPantryService.getUserPantries(userId).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public UserPantryResponse addPantry(UserPantryRequest request) {
        Long userId = getAuthenticatedUserId();
        
        com.smarterrecipe.domain.model.IngredientModel ingredientModel = ingredientService.findOrCreateByName(request.getIngredientName());
        
        UserPantry userPantry = userPantryService.addIngredientToUserPantry(
            userId, 
            ingredientModel.getId(), 
            request.getQuantity(), 
            request.getUnit()
        );
        return mapToResponse(userPantry);
    }

    public void removePantry(Long ingredientId) {
        Long userId = getAuthenticatedUserId();
        userPantryService.removeIngredientFromUserPantry(userId, ingredientId);
    }

    private Long getAuthenticatedUserId() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User tidak ditemukan"));
        return user.getId();
    }

    private UserPantryResponse mapToResponse(UserPantry userPantry) {
        return new UserPantryResponse(
                userPantry.getId(),
                userPantry.getIngredient().getId(),
                userPantry.getIngredient().getName(),
                userPantry.getQuantity(),
                userPantry.getUnit()
        );
    }
}
