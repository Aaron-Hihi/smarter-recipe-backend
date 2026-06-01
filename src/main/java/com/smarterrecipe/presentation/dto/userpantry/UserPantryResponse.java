package com.smarterrecipe.presentation.dto.userpantry;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserPantryResponse {
    private Long id;
    private Long pantryId;
    private String pantryName;
    private String pantryDescription;
}
