package com.smarterrecipe.presentation.controller;

import com.smarterrecipe.application.handler.CookingSuggestionHandler;
import com.smarterrecipe.presentation.dto.RecipeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cooking-suggestions")
@RequiredArgsConstructor
public class CookingSuggestionController {

    private final CookingSuggestionHandler cookingSuggestionHandler;

    @GetMapping
    public ResponseEntity<List<RecipeResponse>> getCookingSuggestions() {
        return ResponseEntity.ok(cookingSuggestionHandler.getCookingSuggestions());
    }
}