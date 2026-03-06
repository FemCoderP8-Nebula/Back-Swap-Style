package com.swapstyle.swapstyle.service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.swapstyle.swapstyle.dto.response.ArticleCardReponseDTO;
import com.swapstyle.swapstyle.entity.Article;
import com.swapstyle.swapstyle.repository.ArticleRepository;
//import java.time.LocalDateTime;
import com.swapstyle.swapstyle.entity.enums.Category;
//import com.swapstyle.swapstyle.entity.enums.State;
import com.swapstyle.swapstyle.entity.enums.PublishedRange;

@Service
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;

    // private final UserService userService;

    public ArticleServiceImpl(ArticleRepository articleRepository, UserService userService) {
        this.articleRepository = articleRepository;
        // this.userService= userService;
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
        List<Article> articles = articleRepository.findAll();

        return articles.stream()
                .filter(a -> {
                    LocalDateTime published = a.getPublished();

                    return switch (range) {
                        case LAST_24HS -> published.isAfter(now.minusHours(24));
                        case LAST_WEEK -> published.isAfter(now.minusWeeks(1));
                        case LAST_MONTH -> published.isAfter(now.minusMonths(1));
                        case OLDERS -> published.isBefore(now.minusMonths(1));
                    };
                })
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

    //LLAMO AL FIND ALL... GASTO INNECESARIO?
    //.map mapper??
}
