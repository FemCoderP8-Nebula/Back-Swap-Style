package com.swapstyle.swapstyle.service;

import java.util.List;

import com.swapstyle.swapstyle.entity.Article;

public interface ArticleService {

public Article createArticle(Article article, Integer idUser );

public List <Article>getAllArticles();

public Article deleArticle(Integer idArticle);

public Article updateArticle(Article article);

}
