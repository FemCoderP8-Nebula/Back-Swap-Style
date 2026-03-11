package com.swapstyle.swapstyle.dto.request.updateArticleDetails;

import com.swapstyle.swapstyle.entity.enums.State;

import jakarta.validation.constraints.NotNull;

public record ArticleStateUpdateDTO(
    @NotNull
    State state
) {

}
