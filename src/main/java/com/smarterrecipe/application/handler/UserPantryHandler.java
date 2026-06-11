package com.smarterrecipe.application.handler;

import com.smarterrecipe.data.entity.User;
import com.smarterrecipe.data.entity.UserPantry;
import com.smarterrecipe.data.repository.UserRepository;
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
    private final UserRepository userRepository;

    public List<UserPantryResponse> getMyPantries() {
        Long userId = getAuthenticatedUserId();
        return userPantryService.getUserPantries(userId).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public UserPantryResponse addPantry(UserPantryRequest request) {
        Long userId = getAuthenticatedUserId();
        UserPantry userPantry = userPantryService.addPantryToUser(userId, request.getPantryId());
        return mapToResponse(userPantry);
    }

    public void removePantry(Long pantryId) {
        Long userId = getAuthenticatedUserId();
        userPantryService.removePantryFromUser(userId, pantryId);
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
                userPantry.getPantry().getId(),
                userPantry.getPantry().getName(),
                userPantry.getPantry().getDescription()
        );
    }
}
