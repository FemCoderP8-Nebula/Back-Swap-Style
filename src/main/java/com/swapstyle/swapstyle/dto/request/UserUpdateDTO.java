package com.swapstyle.swapstyle.dto.request;

import com.swapstyle.swapstyle.entity.enums.Avatar;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserUpdateDTO(
    @NotBlank String userName,
    @NotNull Avatar avatar
) {

}