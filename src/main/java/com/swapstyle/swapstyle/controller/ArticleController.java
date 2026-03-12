package com.swapstyle.swapstyle.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.swapstyle.swapstyle.dto.request.ArticleRequestDTO;
import com.swapstyle.swapstyle.dto.request.updateArticleDetails.ArticleCategoryUpdateDTO;
import com.swapstyle.swapstyle.dto.request.updateArticleDetails.ArticleDescriptionUpdateDTO;
import com.swapstyle.swapstyle.dto.request.updateArticleDetails.ArticlePriceUpdateDTO;
import com.swapstyle.swapstyle.dto.request.updateArticleDetails.ArticleSizeUpdateDTO;
import com.swapstyle.swapstyle.dto.request.updateArticleDetails.ArticleStateUpdateDTO;
import com.swapstyle.swapstyle.dto.request.updateArticleDetails.ArticleTitleUpdateDTO;
import com.swapstyle.swapstyle.dto.response.ArticleCardReponseDTO;
import com.swapstyle.swapstyle.dto.response.ArticleResponseDto;
import com.swapstyle.swapstyle.dto.response.ArticleResponseUpdateDetailDTO;
import com.swapstyle.swapstyle.entity.enums.Category;
import com.swapstyle.swapstyle.entity.enums.PublishedRange;
import com.swapstyle.swapstyle.service.ArticleService;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("api/v1/article")
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @PostMapping("/add/{idUser}")
    public ResponseEntity<ArticleResponseDto> createArticle(@RequestBody ArticleRequestDTO dto,
            @PathVariable Integer idUser) {
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

    // paginación de galería
    @GetMapping("/gallery")
    public ResponseEntity<Page<ArticleCardReponseDTO>> getArticlesGallery(
            @PageableDefault(page = 0, size = 30) Pageable pageable) {
        Page<ArticleCardReponseDTO> articles = articleService.getArticlesGallery(pageable);
        return ResponseEntity.ok(articles);
    }

    // filtro para categoría
    @GetMapping("/categories")
    public ResponseEntity<Category[]> getCategories() {
        return ResponseEntity.ok(Category.values());
    }

    // articulos que ofrece el usuario (para el armario)
    @GetMapping("/user/{idUser}")
    public ResponseEntity<List<ArticleCardReponseDTO>> getArticlesByUser(@PathVariable Integer idUser) {
        List<ArticleCardReponseDTO> articles = articleService.getArticlesByUser(idUser);
        return new ResponseEntity<>(articles, HttpStatus.OK);
    }

    @PatchMapping("/title/{id}")
    public ResponseEntity<ArticleResponseUpdateDetailDTO> updateTitle(@PathVariable Integer id,
            @RequestBody ArticleTitleUpdateDTO dto) {
        ArticleResponseUpdateDetailDTO updated = articleService.updateTitle(id, dto);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @PatchMapping("/description/{id}")
    public ResponseEntity<ArticleResponseUpdateDetailDTO> updateDescriptioEntity(@PathVariable Integer id,
            @RequestBody ArticleDescriptionUpdateDTO dto) {
        ArticleResponseUpdateDetailDTO updated = articleService.updateDescription(id, dto);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @PatchMapping("/size/{id}")
    public ResponseEntity<ArticleResponseUpdateDetailDTO> updateSize(@PathVariable Integer id,
            @RequestBody ArticleSizeUpdateDTO dto) {
        ArticleResponseUpdateDetailDTO updated = articleService.updateSize(id, dto);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @PatchMapping("/price/{id}")
    public ResponseEntity<ArticleResponseUpdateDetailDTO> updatePrice(@PathVariable Integer id,
            @RequestBody ArticlePriceUpdateDTO dto) {
        ArticleResponseUpdateDetailDTO updated = articleService.updatePrice(id, dto);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @PatchMapping("/state/{id}")
    public ResponseEntity<ArticleResponseUpdateDetailDTO> updateState(@PathVariable Integer id,
            @RequestBody ArticleStateUpdateDTO dto) {
        ArticleResponseUpdateDetailDTO updated = articleService.updateState(id, dto);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @PatchMapping("/category/{id}")
    public ResponseEntity<ArticleResponseUpdateDetailDTO> updateCategory(@PathVariable Integer id,
            @RequestBody ArticleCategoryUpdateDTO dto) {
        ArticleResponseUpdateDetailDTO updated = articleService.updateCategory(id, dto);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }
}
