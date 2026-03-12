package com.swapstyle.swapstyle.dto.request.updateArticleDetails;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record ArticlePriceUpdateDTO(
    @NotNull(message = "Price is required")
    @PositiveOrZero(message = "Min zero (0.00)")
    Double price
) {

}
