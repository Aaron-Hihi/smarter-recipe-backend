package com.smarterrecipe.presentation.controller;

import com.smarterrecipe.application.handler.MasterDataHandler;
import com.smarterrecipe.presentation.dto.ApiResponse;
import com.smarterrecipe.presentation.dto.GenericMessageResponse;
import com.smarterrecipe.presentation.dto.masterdata.MasterDataItemRequest;
import com.smarterrecipe.presentation.dto.masterdata.MasterDataItemResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/master-data")
@RequiredArgsConstructor
@PreAuthorize("hasAnyRole('ADMIN', 'MODERATOR')")
public class MasterDataController {

    private final MasterDataHandler masterDataHandler;

    @GetMapping("/ingredients")
    public ResponseEntity<ApiResponse<List<MasterDataItemResponse>>> getIngredients() {
        return ResponseEntity.ok(new ApiResponse<>(masterDataHandler.getIngredients()));
    }

    @PostMapping("/ingredients")
    public ResponseEntity<ApiResponse<MasterDataItemResponse>> createIngredient(@Valid @RequestBody MasterDataItemRequest request) {
        return ResponseEntity.ok(new ApiResponse<>(masterDataHandler.createIngredient(request.getName())));
    }

    @PutMapping("/ingredients/{id}")
    public ResponseEntity<ApiResponse<MasterDataItemResponse>> updateIngredient(@PathVariable Long id, @Valid @RequestBody MasterDataItemRequest request) {
        return ResponseEntity.ok(new ApiResponse<>(masterDataHandler.updateIngredient(id, request.getName())));
    }

    @DeleteMapping("/ingredients/{id}")
    public ResponseEntity<GenericMessageResponse> deleteIngredient(@PathVariable Long id) {
        masterDataHandler.deleteIngredient(id);
        return ResponseEntity.ok(new GenericMessageResponse("Ingredient deleted successfully"));
    }

    @GetMapping("/equipment")
    public ResponseEntity<ApiResponse<List<MasterDataItemResponse>>> getEquipment() {
        return ResponseEntity.ok(new ApiResponse<>(masterDataHandler.getEquipment()));
    }

    @PostMapping("/equipment")
    public ResponseEntity<ApiResponse<MasterDataItemResponse>> createEquipment(@Valid @RequestBody MasterDataItemRequest request) {
        return ResponseEntity.ok(new ApiResponse<>(masterDataHandler.createEquipment(request.getName())));
    }

    @PutMapping("/equipment/{id}")
    public ResponseEntity<ApiResponse<MasterDataItemResponse>> updateEquipment(@PathVariable Long id, @Valid @RequestBody MasterDataItemRequest request) {
        return ResponseEntity.ok(new ApiResponse<>(masterDataHandler.updateEquipment(id, request.getName())));
    }

    @DeleteMapping("/equipment/{id}")
    public ResponseEntity<GenericMessageResponse> deleteEquipment(@PathVariable Long id) {
        masterDataHandler.deleteEquipment(id);
        return ResponseEntity.ok(new GenericMessageResponse("Equipment deleted successfully"));
    }
}
