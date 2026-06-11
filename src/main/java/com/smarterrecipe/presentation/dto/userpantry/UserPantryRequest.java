package com.smarterrecipe.presentation.dto.userpantry;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserPantryRequest {

    @NotBlank(message = "Ingredient name tidak boleh kosong")
    private String ingredientName;

    private Double quantity;
    private String unit;
}
