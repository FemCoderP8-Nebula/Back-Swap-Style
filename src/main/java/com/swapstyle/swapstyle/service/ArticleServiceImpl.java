package com.swapstyle.swapstyle.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.swapstyle.swapstyle.dto.response.ArticleCardReponseDTO;
import com.swapstyle.swapstyle.entity.Article;
import com.swapstyle.swapstyle.repository.ArticleRepository;
import java.time.LocalDateTime;
import com.swapstyle.swapstyle.entity.enums.Category;
import com.swapstyle.swapstyle.entity.enums.State;


@Service
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;

    //private final UserService userService;

    public ArticleServiceImpl(ArticleRepository articleRepository, UserService userService){
        this.articleRepository = articleRepository;
       // this.userService= userService;
    }

    @Override
    public List<ArticleCardReponseDTO> getAllArticles() {
        List<Article> article = articleRepository.findAll();
        return article.stream()
        .map(a -> new ArticleCardReponseDTO(
            a.getTitle(),
            a.getSize(),
            a.getPrice(),
            a.getCategory(),
            a.getState(),
            a.getImage(),
            a.getPublished(),
            a.getUserOffers().getUserName()
        ))
        .toList();
    }

    @Override
    public List<ArticleCardReponseDTO> findByCategory(Category category) {
        List<Article> articles = articleRepository.findByCategory(category);
        if (articles.isEmpty()){
            throw new RuntimeException("No existen artículos para la categoría seleccionada");
        }
        return articles.stream()
        .map(article -> new ArticleCardReponseDTO(
            article.getTitle(),
            article.getSize(),
            article.getPrice(),
            article.getCategory(),
            article.getState(),
            article.getImage(),
            article.getPublished(),
            article.getUserOffers().getUserName()
        ))
        .toList();
    }
//COLLECTOR
//OPTIONAL

 }
