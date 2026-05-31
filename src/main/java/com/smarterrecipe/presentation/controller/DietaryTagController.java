package com.smarterrecipe.presentation.controller;

import com.smarterrecipe.application.handler.DietaryTagHandler;
import com.smarterrecipe.presentation.dto.dietarytag.DietaryTagRequest;
import com.smarterrecipe.presentation.dto.dietarytag.DietaryTagResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/dietary-tags")
@RequiredArgsConstructor
public class DietaryTagController {

    private final DietaryTagHandler dietaryTagHandler;

    @PostMapping
    public ResponseEntity<DietaryTagResponse> createDietaryTag(@Valid @RequestBody DietaryTagRequest request) {
        return ResponseEntity.ok(dietaryTagHandler.createDietaryTag(request));
    }

    @GetMapping
    public ResponseEntity<List<DietaryTagResponse>> getAllDietaryTags() {
        return ResponseEntity.ok(dietaryTagHandler.getAllDietaryTags());
    }
}