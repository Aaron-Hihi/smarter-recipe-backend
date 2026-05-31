package com.smarterrecipe.application.handler;

import com.smarterrecipe.data.entity.Pantry;
import com.smarterrecipe.data.repository.PantryRepository;
import com.smarterrecipe.presentation.dto.PantryRequest;
import com.smarterrecipe.presentation.dto.PantryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PantryHandler {

    private final PantryRepository pantryRepository;

    public PantryResponse createPantry(PantryRequest request) {
        if (pantryRepository.existsByNameIgnoreCase(request.getName())) {
            throw new IllegalArgumentException("Pantry already exists");
        }

        Pantry pantry = new Pantry();
        pantry.setName(request.getName());
        pantry.setDescription(request.getDescription());
        pantry = pantryRepository.save(pantry);

        return new PantryResponse(pantry.getId(), pantry.getName(), pantry.getDescription());
    }

    public List<PantryResponse> getAllPantries() {
        return pantryRepository.findAll().stream()
                .map(pantry -> new PantryResponse(
                        pantry.getId(),
                        pantry.getName(),
                        pantry.getDescription()
                ))
                .collect(Collectors.toList());
    }
}