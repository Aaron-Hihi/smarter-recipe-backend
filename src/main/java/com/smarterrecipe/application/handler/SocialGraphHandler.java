package com.smarterrecipe.application.handler;

import com.smarterrecipe.data.entity.User;
import com.smarterrecipe.data.entity.UserFollows;
import com.smarterrecipe.data.repository.UserRepository;
import com.smarterrecipe.domain.service.SocialGraphService;
import com.smarterrecipe.presentation.dto.socialgraph.FollowCountResponse;
import com.smarterrecipe.presentation.dto.socialgraph.FollowResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SocialGraphHandler {

    private final SocialGraphService socialGraphService;
    private final UserRepository userRepository;

    public List<FollowResponse> getFollowers(Long userId) {
        return socialGraphService.getFollowers(userId).stream()
                .map(follow -> mapToResponse(follow.getId(), follow.getFollower(), follow))
                .collect(Collectors.toList());
    }

    public List<FollowResponse> getFollowing(Long userId) {
        return socialGraphService.getFollowing(userId).stream()
                .map(follow -> mapToResponse(follow.getId(), follow.getFollowee(), follow))
                .collect(Collectors.toList());
    }

    public FollowCountResponse getFollowCounts(Long userId) {
        long followers = socialGraphService.countFollowers(userId);
        long following = socialGraphService.countFollowing(userId);
        return new FollowCountResponse(followers, following);
    }

    public void followUser(Long followeeId) {
        Long followerId = getAuthenticatedUserId();
        socialGraphService.followUser(followerId, followeeId);
    }

    public void unfollowUser(Long followeeId) {
        Long followerId = getAuthenticatedUserId();
        socialGraphService.unfollowUser(followerId, followeeId);
    }

    private Long getAuthenticatedUserId() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User tidak ditemukan"));
        return user.getId();
    }

    private FollowResponse mapToResponse(Long followId, User user, UserFollows follow) {
        return new FollowResponse(
                followId,
                user.getId(),
                user.getUsername(),
                user.getProfilePictureUrl(),
                follow.getCreatedAt()
        );
    }
}
