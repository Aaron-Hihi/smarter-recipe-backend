package com.smarterrecipe.application.handler;

import com.smarterrecipe.data.entity.DietaryTag;
import com.smarterrecipe.data.repository.DietaryTagRepository;
import com.smarterrecipe.presentation.dto.dietarytag.DietaryTagRequest;
import com.smarterrecipe.presentation.dto.dietarytag.DietaryTagResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DietaryTagHandler {

    private final DietaryTagRepository dietaryTagRepository;

    public DietaryTagResponse createDietaryTag(DietaryTagRequest request) {
        if (dietaryTagRepository.existsByNameIgnoreCase(request.getName())) {
            throw new IllegalArgumentException("Dietary Tag already exists");
        }

        DietaryTag dietaryTag = new DietaryTag();
        dietaryTag.setName(request.getName());
        dietaryTag.setDescription(request.getDescription());
        dietaryTag = dietaryTagRepository.save(dietaryTag);

        return new DietaryTagResponse(dietaryTag.getId(), dietaryTag.getName(), dietaryTag.getDescription());
    }

    public List<DietaryTagResponse> getAllDietaryTags() {
        return dietaryTagRepository.findAll().stream()
                .map(tag -> new DietaryTagResponse(
                        tag.getId(),
                        tag.getName(),
                        tag.getDescription()
                ))
                .collect(Collectors.toList());
    }
}