package com.smarterrecipe.domain.validator;

import com.smarterrecipe.data.repository.user.UserFollowsJpaRepository;
import com.smarterrecipe.data.repository.user.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SocialGraphValidator {

    private final UserJpaRepository userRepository;
    private final UserFollowsJpaRepository userFollowsRepository;

    public void validateFollowerExists(Long followerId) {
        if (!userRepository.existsById(followerId)) {
            throw new IllegalArgumentException("User tidak ditemukan");
        }
    }

    public void validateFolloweeExists(Long followeeId) {
        if (!userRepository.existsById(followeeId)) {
            throw new IllegalArgumentException("User yang ingin difollow tidak ditemukan");
        }
    }

    public void validateNotSelfFollow(Long followerId, Long followeeId) {
        if (followerId.equals(followeeId)) {
            throw new IllegalArgumentException("Tidak bisa follow diri sendiri");
        }
    }

    public void validateNotAlreadyFollowing(Long followerId, Long followeeId) {
        if (userFollowsRepository.existsByFollowerIdAndFolloweeId(followerId, followeeId)) {
            throw new IllegalArgumentException("Sudah mengikuti user ini");
        }
    }

    public void validateAlreadyFollowing(Long followerId, Long followeeId) {
        if (!userFollowsRepository.existsByFollowerIdAndFolloweeId(followerId, followeeId)) {
            throw new IllegalArgumentException("Belum mengikuti user ini");
        }
    }
}
