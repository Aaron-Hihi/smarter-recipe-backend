package com.smarterrecipe.presentation.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class DietaryPreferencesRequest {
    private Boolean isVegan;
    private Boolean isVegetarian;
    private Boolean isHalal;
    private Boolean isGlutenFree;
    private Boolean isNutAllergy;
    private Boolean isDairyFree;
}
