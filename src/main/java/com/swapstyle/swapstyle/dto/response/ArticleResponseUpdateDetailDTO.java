package com.swapstyle.swapstyle.dto.response;

public record ArticleResponseUpdateDetailDTO(

        String title,
        String description,
        String size,
        Double price,
        String category,
        String state,
        String image
        ) {

}