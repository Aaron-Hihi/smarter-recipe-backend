package com.smarterrecipe.domain.service;

import com.smarterrecipe.data.entity.User;
import com.smarterrecipe.data.entity.UserFollows;
import com.smarterrecipe.data.repository.user.UserFollowsJpaRepository;
import com.smarterrecipe.data.repository.user.UserJpaRepository;
import com.smarterrecipe.domain.validator.SocialGraphValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SocialGraphService {

    private final UserFollowsJpaRepository userFollowsRepository;
    private final UserJpaRepository userRepository;
    private final SocialGraphValidator socialGraphValidator;

    public List<UserFollows> getFollowers(Long userId) {
        socialGraphValidator.validateFolloweeExists(userId);
        return userFollowsRepository.findAllByFolloweeId(userId);
    }

    public List<UserFollows> getFollowing(Long userId) {
        socialGraphValidator.validateFollowerExists(userId);
        return userFollowsRepository.findAllByFollowerId(userId);
    }

    public long countFollowers(Long userId) {
        socialGraphValidator.validateFolloweeExists(userId);
        return userFollowsRepository.countByFolloweeId(userId);
    }

    public long countFollowing(Long userId) {
        socialGraphValidator.validateFollowerExists(userId);
        return userFollowsRepository.countByFollowerId(userId);
    }

    @Transactional
    public UserFollows followUser(Long followerId, Long followeeId) {
        socialGraphValidator.validateNotSelfFollow(followerId, followeeId);
        socialGraphValidator.validateFollowerExists(followerId);
        socialGraphValidator.validateFolloweeExists(followeeId);
        socialGraphValidator.validateNotAlreadyFollowing(followerId, followeeId);

        User follower = userRepository.findById(followerId).orElseThrow();
        User followee = userRepository.findById(followeeId).orElseThrow();

        UserFollows userFollows = new UserFollows();
        userFollows.setFollower(follower);
        userFollows.setFollowee(followee);

        return userFollowsRepository.save(userFollows);
    }

    @Transactional
    public void unfollowUser(Long followerId, Long followeeId) {
        socialGraphValidator.validateAlreadyFollowing(followerId, followeeId);

        UserFollows userFollows = userFollowsRepository.findByFollowerIdAndFolloweeId(followerId, followeeId).orElseThrow();
        userFollowsRepository.delete(userFollows);
    }
}
