package com.smarterrecipe.application.handler;

import com.smarterrecipe.data.entity.User;
import com.smarterrecipe.data.entity.UserFollows;
import com.smarterrecipe.data.repository.user.UserFollowsJpaRepository;
import com.smarterrecipe.data.repository.user.UserJpaRepository;
import com.smarterrecipe.domain.model.enums.Role;
import com.smarterrecipe.domain.service.UserService;
import com.smarterrecipe.presentation.dto.ApiResponse;
import com.smarterrecipe.presentation.dto.CreatorProfileResponse;
import com.smarterrecipe.presentation.dto.FollowToggleResponse;
import com.smarterrecipe.presentation.dto.user.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CreatorHandler {

    private final UserJpaRepository userJpaRepository;
    private final UserFollowsJpaRepository userFollowsJpaRepository;
    private final UserHandler userHandler;

    @Transactional(readOnly = true)
    public List<CreatorProfileResponse> getCreators(String currentUsername) {
        return userJpaRepository.findAll().stream()
                .filter(u -> u.getRole() == Role.RECIPE_CREATOR)
                .map(u -> toCreatorProfile(u, currentUsername))
                .collect(Collectors.toList());
    }

    @Transactional
    public FollowToggleResponse toggleFollow(Long creatorId, String currentUsername) {
        User currentUser = userJpaRepository.findByUsername(currentUsername)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        User creator = userJpaRepository.findById(creatorId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Creator not found"));

        if (creator.getRole() != Role.RECIPE_CREATOR) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User is not a creator");
        }

        boolean alreadyFollowing = userFollowsJpaRepository
                .existsByFollowerIdAndFolloweeId(currentUser.getId(), creator.getId());

        if (alreadyFollowing) {
            userFollowsJpaRepository.deleteByFollowerIdAndFolloweeId(currentUser.getId(), creator.getId());
            return new FollowToggleResponse("Unfollowed successfully", false);
        } else {
            UserFollows follow = new UserFollows();
            follow.setFollower(currentUser);
            follow.setFollowee(creator);
            userFollowsJpaRepository.save(follow);
            return new FollowToggleResponse("Followed successfully", true);
        }
    }

    private CreatorProfileResponse toCreatorProfile(User u, String currentUsername) {
        boolean isFollowing = false;
        if (currentUsername != null) {
            userJpaRepository.findByUsername(currentUsername).ifPresent(viewer ->
                    isFollowing(viewer.getId(), u.getId()));
            isFollowing = userJpaRepository.findByUsername(currentUsername)
                    .map(viewer -> userFollowsJpaRepository.existsByFollowerIdAndFolloweeId(viewer.getId(), u.getId()))
                    .orElse(false);
        }
        UserResponse userResp = userHandler.getProfile(u.getUsername());
        return CreatorProfileResponse.builder()
                .id(u.getId())
                .user(userResp)
                .isFollowing(isFollowing)
                .topRecipes(List.of())
                .build();
    }

    private void isFollowing(Long viewerId, Long creatorId) {
        // helper — intentionally blank, result used via map above
    }
}
