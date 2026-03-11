package com.swapstyle.swapstyle.dto.request.updateArticleDetails;

import jakarta.validation.constraints.NotBlank;

public record ArticleTitleUpdateDTO(
    @NotBlank
    String title
) {

}
