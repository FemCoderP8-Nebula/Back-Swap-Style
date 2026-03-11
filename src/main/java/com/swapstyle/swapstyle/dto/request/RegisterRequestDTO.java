package com.swapstyle.swapstyle.dto.request;

import com.swapstyle.swapstyle.entity.enums.Role;
import com.swapstyle.swapstyle.entity.enums.Avatar;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record RegisterRequestDTO(
        @NotBlank(message = "User name is required") @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters") String userName,

        @NotBlank(message = "Email is required") @Email(message = "Invalid email format") String email,

        @NotBlank(message = "Password is required") @Size(min = 8, max = 15, message = "Choose a password(8 to 15 characters)") String password,

        @NotNull(message = "Select is requiered") Avatar avatar,

        @NotNull(message = "Role is required") Role role

) {

}