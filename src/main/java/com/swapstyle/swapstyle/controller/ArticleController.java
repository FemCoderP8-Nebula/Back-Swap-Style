package com.swapstyle.swapstyle.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swapstyle.swapstyle.dto.request.ArticleRequestDTO;
import com.swapstyle.swapstyle.dto.response.ArticleCardReponseDTO;
import com.swapstyle.swapstyle.entity.Article;
import com.swapstyle.swapstyle.service.ArticleService;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("api/v1/article")
public class ArticleController {
   
    private final ArticleService articleService;

    public ArticleController(ArticleService articleService){
        this.articleService = articleService;
    }

    @PostMapping("/add")
    public ResponseEntity<Article> createArticle(@RequestBody ArticleRequestDTO dto) {
    Article article = articleService.createArticle(dto);
        
    return new ResponseEntity<>(article, HttpStatus.CREATED);
    }
    

    @GetMapping("/articles")
    public ResponseEntity<List<ArticleCardReponseDTO>> getAllArticles() {
        List<ArticleCardReponseDTO> articles = articleService.getAllArticles();
        return new ResponseEntity<>(articles, HttpStatus.OK);
    }
    

}