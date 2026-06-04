package com.smarterrecipe.domain.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PantryModel {
    private Long id;
    private String name;
    private String description;
}