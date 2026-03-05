package com.swapstyle.swapstyle.service;

import java.util.List;

import com.swapstyle.swapstyle.dto.request.ArticleRequestDTO;
import com.swapstyle.swapstyle.entity.Article;

public interface ArticleService {

public Article createArticle(ArticleRequestDTO dto );

public List <Article>getAllArticles();

public Article getDetail ( Integer id);

public Article deleteArticle(Integer id);

public Article updateArticle(Article article);

}
