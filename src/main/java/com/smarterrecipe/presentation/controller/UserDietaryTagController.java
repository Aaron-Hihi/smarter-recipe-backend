package com.smarterrecipe.presentation.controller;

import com.smarterrecipe.application.handler.UserDietaryTagHandler;
import com.smarterrecipe.presentation.dto.userdietarytag.UserDietaryTagRequest;
import com.smarterrecipe.presentation.dto.userdietarytag.UserDietaryTagResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users/me/dietary-tags")
@RequiredArgsConstructor
public class UserDietaryTagController {

    private final UserDietaryTagHandler userDietaryTagHandler;

    @GetMapping
    public ResponseEntity<List<UserDietaryTagResponse>> getMyDietaryTags() {
        return ResponseEntity.ok(userDietaryTagHandler.getMyDietaryTags());
    }

    @PostMapping
    public ResponseEntity<UserDietaryTagResponse> addDietaryTag(@Valid @RequestBody UserDietaryTagRequest request) {
        return ResponseEntity.ok(userDietaryTagHandler.addDietaryTag(request));
    }

    @DeleteMapping("/{dietaryTagId}")
    public ResponseEntity<Void> removeDietaryTag(@PathVariable Long dietaryTagId) {
        userDietaryTagHandler.removeDietaryTag(dietaryTagId);
        return ResponseEntity.noContent().build();
    }
}
