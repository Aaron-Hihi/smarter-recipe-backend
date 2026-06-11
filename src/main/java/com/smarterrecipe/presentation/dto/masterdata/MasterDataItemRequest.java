package com.smarterrecipe.presentation.dto.masterdata;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MasterDataItemRequest {
    @NotBlank
    private String name;
}
