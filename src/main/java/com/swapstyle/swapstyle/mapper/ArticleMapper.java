package com.swapstyle.swapstyle.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import com.swapstyle.swapstyle.dto.response.ArticleCardReponseDTO;
import com.swapstyle.swapstyle.entity.Article;
import com.swapstyle.swapstyle.repository.ReserveRepository;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring")
public abstract class ArticleMapper {

    @Autowired
    protected ReserveRepository reserveRepository;
    @Mapping(target = "id", source = "idArticle")
    @Mapping(target = "sellerName", expression = "java(article.getUserOffers().getUserName())")
    @Mapping(target = "expiryDate", expression = "java(getExpiryDate(article))")
    public abstract ArticleCardReponseDTO toCardDTO(Article article);

    protected LocalDateTime getExpiryDate(Article article) {
        return reserveRepository
                .findByArticleIdArticle(article.getIdArticle())
                .map(reserve -> reserve.getExpiryDate())
                .orElse(null);
    }
}