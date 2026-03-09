package com.swapstyle.swapstyle.service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.swapstyle.swapstyle.dto.request.ArticleRequestDTO;
import com.swapstyle.swapstyle.entity.Article;
import com.swapstyle.swapstyle.entity.User;
import com.swapstyle.swapstyle.repository.ArticleRepository;


import com.swapstyle.swapstyle.dto.response.ArticleCardReponseDTO;
import com.swapstyle.swapstyle.dto.response.ArticleResponseDto;

//import java.time.LocalDateTime;
import com.swapstyle.swapstyle.entity.enums.Category;
//import com.swapstyle.swapstyle.entity.enums.State;
import com.swapstyle.swapstyle.entity.enums.PublishedRange;
import com.swapstyle.swapstyle.mapper.ArticleMapper;

@Service
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;

    private final UserService userService;

    private final ArticleMapper articleMapper;

    public ArticleServiceImpl(ArticleRepository articleRepository, UserService userService,ArticleMapper articleMapper) {
        this.articleRepository = articleRepository;
        this.userService = userService;
        this.articleMapper=articleMapper;
    }

    @Override
    public ArticleResponseDto createArticle(ArticleRequestDTO dto, Integer idUser) {

        User user = userService.getUserById(idUser);
        
        Article article = new Article();
        article.setTitle(dto.title());
        article.setDescription(dto.description());
        article.setSize(dto.size());
        article.setPrice(dto.price());
        article.setCategory(dto.category());
        article.setState(dto.state());
        article.setImage(dto.image());
        article.setUserOffers(user);

        Article savedArticle = articleRepository.save(article);

        return new ArticleResponseDto(
        savedArticle.getIdArticle(),
        savedArticle.getTitle(),
        savedArticle.getDescription(),
        savedArticle.getSize(),
        savedArticle.getPrice(),
        savedArticle.getCategory().name(),
        savedArticle.getState().name(),
        savedArticle.getImage(),
        savedArticle.getUserOffers().getIdUser());

    }

    @Override
    public List<ArticleCardReponseDTO> getAllArticles() {
        List<Article> article = articleRepository.findAll();
        if (article.isEmpty()) {
            throw new RuntimeException("There's no articles published");
        }
        return article.stream()
                .sorted(Comparator.comparing(Article::getPublished).reversed())
                .map(a -> new ArticleCardReponseDTO(
                        a.getTitle(),
                        a.getSize(),
                        a.getPrice(),
                        a.getCategory(),
                        a.getState(),
                        a.getImage(),
                        a.getPublished(),
                        a.getUserOffers().getUserName()))
                .toList();
    }

    @Override
    public List<ArticleCardReponseDTO> findByCategory(Category category) {
        List<Article> articles = articleRepository.findByCategory(category);
        if (articles.isEmpty()) {
            throw new RuntimeException("Doesn't exist articles for the category selected");
        }
        return articles.stream()
                .sorted(Comparator.comparing(Article::getPublished).reversed())
                .map(article -> new ArticleCardReponseDTO(
                        article.getTitle(),
                        article.getSize(),
                        article.getPrice(),
                        article.getCategory(),
                        article.getState(),
                        article.getImage(),
                        article.getPublished(),
                        article.getUserOffers().getUserName()))
                .toList();
    }
    // COLLECTOR
    // OPTIONAL

    @Override
    public List<ArticleCardReponseDTO> findByPublishedRange(PublishedRange range) {
        LocalDateTime now = LocalDateTime.now();
        List<Article> articles;

        switch (range) {
            case LAST_24HS -> articles = articleRepository.findByPublishedAfter(now.minusHours(24));
            case LAST_WEEK -> articles = articleRepository.findByPublishedAfter(now.minusWeeks(1));
            case LAST_MONTH -> articles = articleRepository.findByPublishedAfter(now.minusMonths(1));
            case OLDERS -> articles = articleRepository.findByPublishedBefore(now.minusMonths(1));
            default -> throw new RuntimeException("Please select a range of time");
        }
        ;

        return articles.stream()
                .sorted(Comparator.comparing(Article::getPublished).reversed())
                .map(a -> new ArticleCardReponseDTO(
                        a.getTitle(),
                        a.getSize(),
                        a.getPrice(),
                        a.getCategory(),
                        a.getState(),
                        a.getImage(),
                        a.getPublished(),
                        a.getUserOffers().getUserName()))
                .toList();
    }

    // .map mapper??


    @Override
    public Page<ArticleCardReponseDTO> getArticlesGallery(Pageable pageable) {
        Page<Article> articles = articleRepository.findAll(pageable);
        return articles.map(articleMapper::toCardDTO);
    }

    @Override
     public List<ArticleCardReponseDTO> getArticlesByUser(Integer idUser) {
    List<Article> articles = articleRepository.findByUserOffers_IdUser(idUser);
    return articles.stream()
        .map(article -> articleMapper.toCardDTO(article))
        .collect(Collectors.toList());
}
}

