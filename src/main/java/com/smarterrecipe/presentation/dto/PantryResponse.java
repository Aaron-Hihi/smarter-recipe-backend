package com.smarterrecipe.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class PantryResponse {
    private Long id;
    private String name;
    private String description;
}