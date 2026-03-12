package com.swapstyle.swapstyle.dto.request;

import com.swapstyle.swapstyle.entity.enums.Avatar;

import jakarta.validation.constraints.NotNull;

public record UserAvatarUpdateDTO(
        @NotNull Avatar avatar) {

}
