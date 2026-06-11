package com.smarterrecipe.presentation.controller;

import com.smarterrecipe.application.handler.SocialGraphHandler;
import com.smarterrecipe.presentation.dto.socialgraph.FollowCountResponse;
import com.smarterrecipe.presentation.dto.socialgraph.FollowResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class SocialGraphController {

    private final SocialGraphHandler socialGraphHandler;

    @GetMapping("/{userId}/followers")
    public ResponseEntity<List<FollowResponse>> getFollowers(@PathVariable Long userId) {
        return ResponseEntity.ok(socialGraphHandler.getFollowers(userId));
    }

    @GetMapping("/{userId}/following")
    public ResponseEntity<List<FollowResponse>> getFollowing(@PathVariable Long userId) {
        return ResponseEntity.ok(socialGraphHandler.getFollowing(userId));
    }

    @GetMapping("/{userId}/follow-counts")
    public ResponseEntity<FollowCountResponse> getFollowCounts(@PathVariable Long userId) {
        return ResponseEntity.ok(socialGraphHandler.getFollowCounts(userId));
    }

    @PostMapping("/{userId}/follow")
    public ResponseEntity<Void> followUser(@PathVariable Long userId) {
        socialGraphHandler.followUser(userId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{userId}/follow")
    public ResponseEntity<Void> unfollowUser(@PathVariable Long userId) {
        socialGraphHandler.unfollowUser(userId);
        return ResponseEntity.noContent().build();
    }
}
