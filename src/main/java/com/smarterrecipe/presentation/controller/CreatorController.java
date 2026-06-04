package com.smarterrecipe.presentation.controller;

import com.smarterrecipe.presentation.dto.ApiResponse;
import com.smarterrecipe.presentation.dto.CreatorProfileResponse;
import com.smarterrecipe.presentation.dto.FollowToggleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/creators")
@RequiredArgsConstructor
public class CreatorController {

    @GetMapping
    public ResponseEntity<ApiResponse<List<CreatorProfileResponse>>> getCreators() {
        // Stub implementation
        return ResponseEntity.ok(new ApiResponse<>(List.of()));
    }

    @PostMapping("/{id}/follow")
    public ResponseEntity<FollowToggleResponse> toggleFollow(@PathVariable Long id) {
        // Stub implementation
        return ResponseEntity.ok(new FollowToggleResponse("Followed successfully", true));
    }
}
