package com.swapstyle.swapstyle.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.swapstyle.swapstyle.dto.request.ArticleRequestDTO;
import com.swapstyle.swapstyle.dto.response.ArticleCardReponseDTO;
import com.swapstyle.swapstyle.dto.response.ArticleResponseDto;
import com.swapstyle.swapstyle.entity.enums.Category;
import com.swapstyle.swapstyle.entity.enums.PublishedRange;
import com.swapstyle.swapstyle.service.ArticleService;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("api/v1/article")
public class ArticleController {
   
    private final ArticleService articleService;

    public ArticleController(ArticleService articleService){
        this.articleService = articleService;
    }

    @PostMapping("/add/{idUser}")
    public ResponseEntity<ArticleResponseDto> createArticle(@RequestBody ArticleRequestDTO dto, @PathVariable Integer idUser) {
    ArticleResponseDto article = articleService.createArticle(dto, idUser);
        
    return new ResponseEntity<>(article, HttpStatus.CREATED);
    }
    

    @GetMapping("/articles")
    public ResponseEntity<List<ArticleCardReponseDTO>> getAllArticles() {
        List<ArticleCardReponseDTO> articles = articleService.getAllArticles();
        return new ResponseEntity<>(articles, HttpStatus.OK);
    }

    @GetMapping("/articlesByCategory")
    public ResponseEntity<List<ArticleCardReponseDTO>> findByCategory(@RequestParam Category category) {
        List<ArticleCardReponseDTO> articles = articleService.findByCategory(category);
        return new ResponseEntity<>(articles, HttpStatus.OK);
    }

    
    @GetMapping("/articlesByPublishedRange")
    public ResponseEntity<List<ArticleCardReponseDTO>> findByPublishedRange(@RequestParam PublishedRange range) {
        List<ArticleCardReponseDTO> articles = articleService.findByPublishedRange(range);
        return new ResponseEntity<>(articles, HttpStatus.OK);
    }
    

}