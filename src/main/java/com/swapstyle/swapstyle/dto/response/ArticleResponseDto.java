package com.swapstyle.swapstyle.dto.response;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public record ArticleResponseDto(

        Integer id,
        String title,
        String description,
        String size,
        Double price,
        String category,
        String state,
        String image,
        Integer idUser,
        String sellerName,
        Boolean isReserved,
        Integer reservedByUserId,
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime expiryDate) {

}
