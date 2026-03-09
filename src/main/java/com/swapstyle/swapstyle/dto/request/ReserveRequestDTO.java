package com.swapstyle.swapstyle.dto.request;

import jakarta.validation.constraints.NotNull;

public record ReserveRequestDTO(
    @NotNull(message = "User ID is required")
    Integer userId,

    @NotNull(message = "Article ID is required")
    Integer articleId
) {

}
