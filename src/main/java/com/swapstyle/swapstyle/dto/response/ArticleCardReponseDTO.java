package com.swapstyle.swapstyle.dto.response;

import java.lang.Thread.State;
import java.time.LocalDateTime;

import com.swapstyle.swapstyle.entity.User;
import com.swapstyle.swapstyle.entity.enums.Category;

public record ArticleCardReponseDTO(
    String title,
    String size,
    Double price,
    Category category,
    State state,
    String image,
    LocalDateTime published,
    User userOffers
) {

   
}
