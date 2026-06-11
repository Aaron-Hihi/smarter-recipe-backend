package com.smarterrecipe.presentation.dto.userdietarytag;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDietaryTagRequest {

    @NotNull(message = "Dietary tag ID tidak boleh kosong")
    private Long dietaryTagId;
}
