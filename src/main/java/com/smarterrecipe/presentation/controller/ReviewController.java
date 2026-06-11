package com.smarterrecipe.presentation.controller;

import com.smarterrecipe.application.handler.ReviewHandler;
import com.smarterrecipe.presentation.dto.ApiResponse;
import com.smarterrecipe.presentation.dto.ReviewRequest;
import com.smarterrecipe.presentation.dto.ReviewResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/recipes/{recipeId}/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewHandler reviewHandler;

    @GetMapping
    public ResponseEntity<ApiResponse<List<ReviewResponse>>> getReviews(@PathVariable Long recipeId) {
        return ResponseEntity.ok(new ApiResponse<>(reviewHandler.getReviewsForRecipe(recipeId)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ReviewResponse>> addReview(
            @PathVariable Long recipeId,
            @Valid @RequestBody ReviewRequest request,
            Principal principal) {
        return ResponseEntity.ok(new ApiResponse<>(reviewHandler.addReview(recipeId, principal.getName(), request)));
    }
}
