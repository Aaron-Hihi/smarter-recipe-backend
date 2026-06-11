package com.smarterrecipe.data.repository.user;

import com.smarterrecipe.data.entity.UserFollows;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserFollowsJpaRepository extends JpaRepository<UserFollows, Long> {
    Optional<UserFollows> findByFollowerIdAndFolloweeId(Long followerId, Long followeeId);
    long countByFolloweeId(Long followeeId);
    long countByFollowerId(Long followerId);
    void deleteByFollowerIdAndFolloweeId(Long followerId, Long followeeId);
    boolean existsByFollowerIdAndFolloweeId(Long followerId, Long followeeId);
    List<UserFollows> findAllByFolloweeId(Long followeeId);
    List<UserFollows> findAllByFollowerId(Long followerId);
}
