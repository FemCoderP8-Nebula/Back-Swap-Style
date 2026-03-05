package com.swapstyle.swapstyle.service;

import java.util.List;

import com.swapstyle.swapstyle.entity.Article;

public interface ArticleService {

public Article createArticle(Article article, Integer id );

public List <Article>getAllArticles();

public Article getDetail ( Integer id);

public Article deleArticle(Integer id);

public Article updateArticle(Article article);

}
