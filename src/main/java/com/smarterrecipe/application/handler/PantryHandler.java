package com.smarterrecipe.application.handler;

import com.smarterrecipe.domain.model.PantryModel;
import com.smarterrecipe.domain.service.PantryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PantryHandler {
    private final PantryService pantryService;

    public PantryModel createPantry(String name, String quantity, String category) {
        // We serialize quantity and category into description for MVP
        String description = (category != null ? category : "") + "|" + (quantity != null ? quantity : "");

        PantryModel model = PantryModel.builder()
                .name(name)
                .description(description)
                .build();

        return pantryService.create(model);
    }

    public List<PantryModel> getAllPantries() {
        return pantryService.getAll();
    }

    public void deletePantry(Long id) {
        pantryService.delete(id);
    }
}