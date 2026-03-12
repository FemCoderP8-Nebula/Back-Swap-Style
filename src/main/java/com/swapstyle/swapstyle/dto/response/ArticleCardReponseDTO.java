package com.swapstyle.swapstyle.dto.response;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.swapstyle.swapstyle.entity.enums.Category;
import com.swapstyle.swapstyle.entity.enums.State;

public record ArticleCardReponseDTO(
        Integer id,
        String title,
        String size,
        Double price,
        Category category,
        State state,
        String image,
        @JsonFormat(pattern = "yyyy/MM/dd") LocalDateTime published,
        String sellerName,
        Boolean isReserved,
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime expiryDate) {

}
