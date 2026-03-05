package com.swapstyle.swapstyle.service;

import java.util.List;

import com.swapstyle.swapstyle.dto.response.ArticleCardReponseDTO;
import com.swapstyle.swapstyle.entity.enums.Category;

public interface ArticleService {

// public Article createArticle(Article article, Integer id );

// public Article getDetail ( Integer id);

// public Article deleArticle(Integer id);

// public Article updateArticle(Article article);


public List<ArticleCardReponseDTO> getAllArticles();

public List<ArticleCardReponseDTO> findByCategory(Category category);

}
