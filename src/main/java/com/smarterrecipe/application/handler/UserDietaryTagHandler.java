package com.smarterrecipe.application.handler;

import com.smarterrecipe.data.entity.User;
import com.smarterrecipe.data.entity.UserDietaryTag;
import com.smarterrecipe.data.repository.user.UserJpaRepository;
import com.smarterrecipe.domain.service.UserDietaryTagService;
import com.smarterrecipe.presentation.dto.userdietarytag.UserDietaryTagRequest;
import com.smarterrecipe.presentation.dto.userdietarytag.UserDietaryTagResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserDietaryTagHandler {

    private final UserDietaryTagService userDietaryTagService;
    private final UserJpaRepository userRepository;

    public List<UserDietaryTagResponse> getMyDietaryTags() {
        Long userId = getAuthenticatedUserId();
        return userDietaryTagService.getUserDietaryTags(userId).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public UserDietaryTagResponse addDietaryTag(UserDietaryTagRequest request) {
        Long userId = getAuthenticatedUserId();
        UserDietaryTag userDietaryTag = userDietaryTagService.addDietaryTagToUser(userId, request.getDietaryTagId());
        return mapToResponse(userDietaryTag);
    }

    public void removeDietaryTag(Long dietaryTagId) {
        Long userId = getAuthenticatedUserId();
        userDietaryTagService.removeDietaryTagFromUser(userId, dietaryTagId);
    }

    private Long getAuthenticatedUserId() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User tidak ditemukan"));
        return user.getId();
    }

    private UserDietaryTagResponse mapToResponse(UserDietaryTag userDietaryTag) {
        return new UserDietaryTagResponse(
                userDietaryTag.getId(),
                userDietaryTag.getDietaryTag().getId(),
                userDietaryTag.getDietaryTag().getName(),
                userDietaryTag.getDietaryTag().getDescription()
        );
    }
}
