package com.swapstyle.swapstyle.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ContactRequestDTO(
        @NotBlank(message = "User name is required") String name,

        @NotBlank(message = "Email is required") @Email(message = "Invalid email format") String email,

        @NotBlank(message = "City is required") String city,

        @NotBlank(message = "A message is required") @Size(min = 2, max = 500, message = "The message can't be exceed of 500 characters") String message) {

}
