package com.smarterrecipe.presentation.controller;

import com.smarterrecipe.application.handler.CreatorHandler;
import com.smarterrecipe.presentation.dto.ApiResponse;
import com.smarterrecipe.presentation.dto.CreatorProfileResponse;
import com.smarterrecipe.presentation.dto.FollowToggleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/creators")
@RequiredArgsConstructor
public class CreatorController {

    private final CreatorHandler creatorHandler;

    @GetMapping
    public ResponseEntity<ApiResponse<List<CreatorProfileResponse>>> getCreators(Principal principal) {
        String username = principal != null ? principal.getName() : null;
        return ResponseEntity.ok(new ApiResponse<>(creatorHandler.getCreators(username)));
    }

    @PostMapping("/{id}/follow")
    public ResponseEntity<FollowToggleResponse> toggleFollow(@PathVariable Long id, Principal principal) {
        return ResponseEntity.ok(creatorHandler.toggleFollow(id, principal.getName()));
    }
}
