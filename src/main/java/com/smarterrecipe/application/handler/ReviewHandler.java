package com.smarterrecipe.application.handler;

import com.smarterrecipe.data.entity.Recipe;
import com.smarterrecipe.data.entity.Review;
import com.smarterrecipe.data.entity.User;
import com.smarterrecipe.data.repository.recipe.RecipeJpaRepository;
import com.smarterrecipe.data.repository.recipe.ReviewJpaRepository;
import com.smarterrecipe.data.repository.user.UserJpaRepository;
import com.smarterrecipe.presentation.dto.ReviewRequest;
import com.smarterrecipe.presentation.dto.ReviewResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewHandler {

    private final ReviewJpaRepository reviewJpaRepository;
    private final RecipeJpaRepository recipeJpaRepository;
    private final UserJpaRepository userJpaRepository;

    @Transactional(readOnly = true)
    public List<ReviewResponse> getReviewsForRecipe(Long recipeId) {
        return reviewJpaRepository.findByRecipeIdOrderByCreatedAtDesc(recipeId)
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public ReviewResponse addReview(Long recipeId, String username, ReviewRequest request) {
        User user = userJpaRepository.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        Recipe recipe = recipeJpaRepository.findById(recipeId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Recipe not found"));

        // Prevent duplicate reviews
        if (reviewJpaRepository.existsByUserIdAndRecipeId(user.getId(), recipeId)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "You have already reviewed this recipe");
        }

        Review review = new Review();
        review.setUser(user);
        review.setRecipe(recipe);
        review.setRating(request.getRating());
        review.setComment(request.getComment());
        reviewJpaRepository.save(review);

        // Recalculate average rating on the Recipe
        recalculateRating(recipe);

        return toResponse(review);
    }

    private void recalculateRating(Recipe recipe) {
        List<Review> allReviews = reviewJpaRepository.findByRecipeIdOrderByCreatedAtDesc(recipe.getId());
        int total = allReviews.size();
        double average = allReviews.stream()
                .mapToInt(Review::getRating)
                .average()
                .orElse(0.0);

        recipe.setTotalReviews(total);
        recipe.setAverageRating(Math.round(average * 10.0) / 10.0);
        recipeJpaRepository.save(recipe);
    }

    private ReviewResponse toResponse(Review r) {
        return ReviewResponse.builder()
                .id(r.getId())
                .recipeId(r.getRecipe().getId())
                .userId(r.getUser().getId())
                .username(r.getUser().getUsername())
                .profileImageUrl(r.getUser().getProfilePictureUrl())
                .rating(r.getRating())
                .comment(r.getComment())
                .createdAt(r.getCreatedAt())
                .build();
    }
}
