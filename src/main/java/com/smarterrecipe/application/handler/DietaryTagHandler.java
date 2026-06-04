package com.smarterrecipe.application.handler;

import com.smarterrecipe.domain.model.DietaryTagModel;
import com.smarterrecipe.domain.service.DietaryTagService;
import com.smarterrecipe.presentation.dto.dietarytag.DietaryTagRequest;
import com.smarterrecipe.presentation.dto.dietarytag.DietaryTagResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DietaryTagHandler {

    private final DietaryTagService dietaryTagService;

    public DietaryTagResponse createDietaryTag(DietaryTagRequest request) {
        DietaryTagModel model = DietaryTagModel.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();

        DietaryTagModel saved = dietaryTagService.create(model);
        return new DietaryTagResponse(saved.getId(), saved.getName(), saved.getDescription());
    }

    public List<DietaryTagResponse> getAllDietaryTags() {
        return dietaryTagService.getAll().stream()
                .map(tag -> new DietaryTagResponse(tag.getId(), tag.getName(), tag.getDescription()))
                .collect(Collectors.toList());
    }
}