package com.swapstyle.swapstyle.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.swapstyle.swapstyle.dto.response.ArticleCardReponseDTO;
import com.swapstyle.swapstyle.entity.Article;

@Mapper(componentModel = "spring")
public interface ArticleMapper {

   @Mapping(target = "sellerName", expression = "java(article.getUserOffers().getUserName())")
    ArticleCardReponseDTO toCardDTO(Article article);
}
