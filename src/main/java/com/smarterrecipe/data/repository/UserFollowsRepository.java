package com.smarterrecipe.data.repository;

import com.smarterrecipe.data.entity.UserFollows;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserFollowsRepository extends JpaRepository<UserFollows, Long> {
    boolean existsByFollowerIdAndFolloweeId(Long followerId, Long followeeId);
    Optional<UserFollows> findByFollowerIdAndFolloweeId(Long followerId, Long followeeId);
    List<UserFollows> findAllByFolloweeId(Long followeeId);
    List<UserFollows> findAllByFollowerId(Long followerId);
    long countByFolloweeId(Long followeeId);
    long countByFollowerId(Long followerId);
}
