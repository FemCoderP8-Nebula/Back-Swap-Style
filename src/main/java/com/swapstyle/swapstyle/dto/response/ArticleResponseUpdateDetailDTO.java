package com.swapstyle.swapstyle.dto.response;

public record ArticleResponseUpdateDetailDTO(

        Integer idArticle,
        String title,
        String description,
        String size,
        Double price,
        String category,
        String state,
        String image,
        Integer idUser,
        String published
        ) {

}