package com.swapstyle.swapstyle.dto.request.updateArticleDetails;

import com.swapstyle.swapstyle.entity.enums.Category;

import jakarta.validation.constraints.NotNull;

public record ArticleCategoryUpdateDTO(
    @NotNull
    Category category
) {

}
