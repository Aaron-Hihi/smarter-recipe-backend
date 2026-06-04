package com.smarterrecipe.presentation.controller;

import com.smarterrecipe.application.handler.PantryHandler;
import com.smarterrecipe.presentation.dto.pantry.PantryRequest;
import com.smarterrecipe.presentation.dto.pantry.PantryResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.smarterrecipe.domain.model.PantryModel;
import com.smarterrecipe.presentation.dto.ApiResponse;
import com.smarterrecipe.presentation.dto.GenericMessageResponse;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/pantry")
@RequiredArgsConstructor
public class PantryController {

    private final PantryHandler pantryHandler;

    @PostMapping
    public ResponseEntity<PantryResponse> createPantry(@Valid @RequestBody PantryRequest request) {
        PantryModel model = pantryHandler.createPantry(request.getName(), request.getQuantity(), request.getCategory());
        return ResponseEntity.ok(toResponse(model));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<PantryResponse>>> getAllPantries() {
        List<PantryResponse> responses = pantryHandler.getAllPantries().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(new ApiResponse<>(responses));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GenericMessageResponse> deletePantry(@PathVariable Long id) {
        pantryHandler.deletePantry(id);
        return ResponseEntity.ok(new GenericMessageResponse("Item deleted successfully"));
    }

    private PantryResponse toResponse(PantryModel model) {
        String[] parts = model.getDescription() != null ? model.getDescription().split("\\|") : new String[]{"", ""};
        String category = parts.length > 0 ? parts[0] : "";
        String quantity = parts.length > 1 ? parts[1] : "";
        return new PantryResponse(model.getId(), model.getName(), quantity, category);
    }
}