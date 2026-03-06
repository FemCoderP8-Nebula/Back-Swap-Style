package com.swapstyle.swapstyle.dto.response;

public record ArticleResponseDto(

        Integer id,
        String title,
        String description,
        String size,
        Double price,
        String category,
        String state,
        String image,
        Integer idUser) {

}
