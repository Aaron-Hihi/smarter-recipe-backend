package com.smarterrecipe.presentation.dto.userdietarytag;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserDietaryTagResponse {
    private Long id;
    private Long dietaryTagId;
    private String dietaryTagName;
    private String dietaryTagDescription;
}
