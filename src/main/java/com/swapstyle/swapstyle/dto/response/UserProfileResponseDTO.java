package com.swapstyle.swapstyle.dto.response;

import com.swapstyle.swapstyle.entity.enums.Avatar;
import com.swapstyle.swapstyle.entity.enums.Role;

public record UserProfileResponseDTO(
    Integer Id,
    String userName,
    String email,
    Avatar avatar,
    Role role

) {
    
}