package com.swapstyle.swapstyle.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.swapstyle.swapstyle.entity.Article;
import com.swapstyle.swapstyle.entity.User;
import com.swapstyle.swapstyle.repository.ArticleRepository;


@Service
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;

    private final UserService userService;

    @Override
    public Article createArticle(Article article, Integer idUser) {
        User user = userService.getUserById(idUser);
        return articleRepository.save(article);
       
    }

    @Override
    public List<Article> getAllArticles() {
       
    }

    @Override
    public Article deleArticle(Integer idArticle) {
     
    }
    @Override
    public Article updateArticle(Article article) {
    
    }

}
