package com.swapstyle.swapstyle.dto.request.updateArticleDetails;

import jakarta.validation.constraints.NotBlank;

public record ArticleDescriptionUpdateDTO(
    @NotBlank(message = "Description required, maximun 250 characters.")
    String description
) {

}
