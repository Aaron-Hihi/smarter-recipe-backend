package com.smarterrecipe.presentation.controller;

import com.smarterrecipe.application.handler.CookingSuggestionHandler;
import com.smarterrecipe.presentation.dto.ApiResponse;
import com.smarterrecipe.presentation.dto.CookingSuggestionRequest;
import com.smarterrecipe.presentation.dto.SuggestionResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cooking-suggestions")
@RequiredArgsConstructor
public class CookingSuggestionController {

    private final CookingSuggestionHandler cookingSuggestionHandler;

    @PostMapping("/search")
    public ResponseEntity<ApiResponse<List<SuggestionResponse>>> getCookingSuggestions(
            @Valid @RequestBody CookingSuggestionRequest request) {
        return ResponseEntity.ok(new ApiResponse<>(cookingSuggestionHandler.getSuggestions(request)));
    }
}