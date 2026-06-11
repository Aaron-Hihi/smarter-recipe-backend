package com.smarterrecipe.application.handler;

import com.smarterrecipe.data.entity.User;
import com.smarterrecipe.data.entity.UserFollows;
import com.smarterrecipe.data.repository.UserRepository;
import com.smarterrecipe.domain.service.AuditLogService;
import com.smarterrecipe.domain.service.NotificationService;
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
    private final AuditLogService auditLogService;
    private final NotificationService notificationService;
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
        User follower = getUserById(followerId);

        socialGraphService.followUser(followerId, followeeId);

        auditLogService.record(
                followerId,
                "FOLLOW_USER",
                "USER",
                followeeId,
                follower.getUsername() + " mengikuti user ID " + followeeId,
                null
        );

        notificationService.send(
                followeeId,
                "Kamu punya follower baru!",
                follower.getUsername() + " mulai mengikuti kamu."
        );
    }

    public void unfollowUser(Long followeeId) {
        Long followerId = getAuthenticatedUserId();
        User follower = getUserById(followerId);

        socialGraphService.unfollowUser(followerId, followeeId);

        auditLogService.record(
                followerId,
                "UNFOLLOW_USER",
                "USER",
                followeeId,
                follower.getUsername() + " berhenti mengikuti user ID " + followeeId,
                null
        );
    }

    private Long getAuthenticatedUserId() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User tidak ditemukan"));
        return user.getId();
    }

    private User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User tidak ditemukan"));
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
