package com.smarterrecipe.domain.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DietaryTagModel {
    private Long id;
    private String name;
    private String description;
}