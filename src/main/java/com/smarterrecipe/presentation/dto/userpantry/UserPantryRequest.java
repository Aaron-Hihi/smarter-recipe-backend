package com.smarterrecipe.presentation.dto.userpantry;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserPantryRequest {

    @NotNull(message = "Pantry ID tidak boleh kosong")
    private Long pantryId;
}
