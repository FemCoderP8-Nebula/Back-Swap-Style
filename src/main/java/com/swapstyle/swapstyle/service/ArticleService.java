package com.swapstyle.swapstyle.service;

import java.util.List;

import com.swapstyle.swapstyle.dto.request.ArticleRequestDTO;
import com.swapstyle.swapstyle.dto.response.ArticleCardReponseDTO;
import com.swapstyle.swapstyle.entity.Article;

public interface ArticleService {

public Article createArticle(ArticleRequestDTO dto );

public List<ArticleCardReponseDTO> getAllArticles();


// public Article getDetail ( Integer id);

// public Article deleArticle(Integer id);

// public Article deleteArticle(Integer id);




}
