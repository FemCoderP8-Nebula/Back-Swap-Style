package com.swapstyle.swapstyle.dto.request.updateArticleDetails;

import jakarta.validation.constraints.NotBlank;

public record ArticleTitleUpdateDTO(
    @NotBlank(message = "Title is required, maximun 50 characters.")
    String title
) {

}
