package com.smarterrecipe.presentation.controller;

import com.smarterrecipe.application.handler.PantryHandler;
import com.smarterrecipe.presentation.dto.pantry.PantryRequest;
import com.smarterrecipe.presentation.dto.pantry.PantryResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pantries")
@RequiredArgsConstructor
public class PantryController {

    private final PantryHandler pantryHandler;

    @PostMapping
    public ResponseEntity<PantryResponse> createPantry(@Valid @RequestBody PantryRequest request) {
        return ResponseEntity.ok(pantryHandler.createPantry(request));
    }

    @GetMapping
    public ResponseEntity<List<PantryResponse>> getAllPantries() {
        return ResponseEntity.ok(pantryHandler.getAllPantries());
    }
}