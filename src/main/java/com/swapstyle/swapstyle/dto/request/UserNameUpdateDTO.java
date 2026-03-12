package com.swapstyle.swapstyle.dto.request;

import jakarta.validation.constraints.NotBlank;

public record UserNameUpdateDTO(
        @NotBlank String userName) {

}
