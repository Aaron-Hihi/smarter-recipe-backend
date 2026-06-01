package com.smarterrecipe.presentation.controller;

import com.smarterrecipe.application.handler.UserPantryHandler;
import com.smarterrecipe.presentation.dto.userpantry.UserPantryRequest;
import com.smarterrecipe.presentation.dto.userpantry.UserPantryResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users/me/pantries")
@RequiredArgsConstructor
public class UserPantryController {

    private final UserPantryHandler userPantryHandler;

    @GetMapping
    public ResponseEntity<List<UserPantryResponse>> getMyPantries() {
        return ResponseEntity.ok(userPantryHandler.getMyPantries());
    }

    @PostMapping
    public ResponseEntity<UserPantryResponse> addPantry(@Valid @RequestBody UserPantryRequest request) {
        return ResponseEntity.ok(userPantryHandler.addPantry(request));
    }

    @DeleteMapping("/{pantryId}")
    public ResponseEntity<Void> removePantry(@PathVariable Long pantryId) {
        userPantryHandler.removePantry(pantryId);
        return ResponseEntity.noContent().build();
    }
}
