package com.swapstyle.swapstyle.dto.request.updateArticleDetails;

import jakarta.validation.constraints.NotBlank;

public record ArticleSizeUpdateDTO(
        @NotBlank(message = "Size is required")
        String size) {

}
