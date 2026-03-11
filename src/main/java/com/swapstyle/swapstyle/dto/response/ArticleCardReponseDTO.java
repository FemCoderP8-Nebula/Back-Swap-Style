package com.swapstyle.swapstyle.dto.response;


import java.time.LocalDateTime;
import com.swapstyle.swapstyle.entity.enums.Category;
import com.swapstyle.swapstyle.entity.enums.State;

public record ArticleCardReponseDTO(
    String title,
    String size,
    Double price,
    Category category,
    State state,
    String image,
    LocalDateTime published,
    String sellerName,
    Boolean isReserved
) {
   
}
