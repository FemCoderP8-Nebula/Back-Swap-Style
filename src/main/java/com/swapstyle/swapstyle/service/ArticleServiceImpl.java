package com.swapstyle.swapstyle.service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.swapstyle.swapstyle.dto.request.ArticleRequestDTO;
import com.swapstyle.swapstyle.dto.request.updateArticleDetails.*;
import com.swapstyle.swapstyle.entity.Article;
import com.swapstyle.swapstyle.entity.Reserve;
import com.swapstyle.swapstyle.entity.User;
import com.swapstyle.swapstyle.repository.ArticleRepository;

import com.swapstyle.swapstyle.dto.response.ArticleCardReponseDTO;
import com.swapstyle.swapstyle.dto.response.ArticleResponseDto;
import com.swapstyle.swapstyle.dto.response.ArticleResponseUpdateDetailDTO;

import com.swapstyle.swapstyle.repository.ReserveRepository;

import com.swapstyle.swapstyle.entity.enums.Category;
import com.swapstyle.swapstyle.entity.enums.PublishedRange;
import com.swapstyle.swapstyle.entity.enums.State;
import com.swapstyle.swapstyle.mapper.ArticleMapper;
import org.springframework.data.domain.Sort;

@Service
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;

    private final UserService userService;

    private final ArticleMapper articleMapper;

    private final ReserveRepository reserveRepository;

    public ArticleServiceImpl(ArticleRepository articleRepository, UserService userService, ArticleMapper articleMapper,
            ReserveRepository reserveRepository) {
        this.articleRepository = articleRepository;
        this.userService = userService;
        this.articleMapper = articleMapper;
        this.reserveRepository = reserveRepository;
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
                savedArticle.getUserOffers().getIdUser(),
                savedArticle.getUserOffers().getUserName(),
                false,
                null,
                null);
    }

    @Override
    public List<ArticleCardReponseDTO> getAllArticles() {
        List<Article> article = articleRepository.findAll();
        if (article.isEmpty()) {
            throw new RuntimeException("There's no articles published");
        }
        return article.stream()
                .sorted(Comparator.comparing(Article::getPublished).reversed())
                .map(a -> {
                    LocalDateTime expiryDate = reserveRepository
                            .findByArticleIdArticle(a.getIdArticle())
                            .map(Reserve::getExpiryDate)
                            .orElse(null);

                    return new ArticleCardReponseDTO(
                            a.getIdArticle(),
                            a.getTitle(),
                            a.getSize(),
                            a.getPrice(),
                            a.getCategory(),
                            a.getState(),
                            a.getImage(),
                            a.getPublished(),
                            a.getUserOffers().getUserName(),
                            a.getIsReserved(),
                            expiryDate);
                })
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
                .map(a -> {
                    LocalDateTime expiryDate = reserveRepository
                            .findByArticleIdArticle(a.getIdArticle())
                            .map(Reserve::getExpiryDate)
                            .orElse(null);

                    return new ArticleCardReponseDTO(
                            a.getIdArticle(),
                            a.getTitle(),
                            a.getSize(),
                            a.getPrice(),
                            a.getCategory(),
                            a.getState(),
                            a.getImage(),
                            a.getPublished(),
                            a.getUserOffers().getUserName(),
                            a.getIsReserved(),
                            expiryDate);
                })
                .toList();
    }

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

        Comparator<Article> comparator = (range == PublishedRange.OLDERS)
                ? Comparator.comparing(Article::getPublished)
                : Comparator.comparing(Article::getPublished).reversed();

        return articles.stream()
                .sorted(comparator)
                .map(a -> {
                    LocalDateTime expiryDate = reserveRepository
                            .findByArticleIdArticle(a.getIdArticle())
                            .map(Reserve::getExpiryDate)
                            .orElse(null);

                    return new ArticleCardReponseDTO(
                            a.getIdArticle(),
                            a.getTitle(),
                            a.getSize(),
                            a.getPrice(),
                            a.getCategory(),
                            a.getState(),
                            a.getImage(),
                            a.getPublished(),
                            a.getUserOffers().getUserName(),
                            a.getIsReserved(),
                            expiryDate);
                })
                .toList();
    }

    @Override
    public Page<ArticleCardReponseDTO> getArticlesGallery(Pageable pageable) {
        Page<Article> articles = articleRepository.findAll(
                PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(),
                        Sort.by(Sort.Direction.DESC, "published")));
        return articles.map(articleMapper::toCardDTO);
    }

    @Override
    public List<ArticleCardReponseDTO> getArticlesByUser(Integer idUser) {
        List<Article> articles = articleRepository.findByUserOffers_IdUser(idUser);
        return articles.stream()
                .map(article -> articleMapper.toCardDTO(article))
                .collect(Collectors.toList());
    }

    // UPDATES DETAIL ARTICLE

    @Override
    public ArticleResponseUpdateDetailDTO updateTitle(Integer id, ArticleTitleUpdateDTO dto) {
        Article article = articleRepository.findById(id).orElseThrow(() -> new RuntimeException("Article not found"));
        article.setTitle(dto.title());
        Article updatedArticle = articleRepository.save(article);
        return new ArticleResponseUpdateDetailDTO(
                updatedArticle.getIdArticle(),
                updatedArticle.getTitle(),
                updatedArticle.getDescription(),
                updatedArticle.getSize(),
                updatedArticle.getPrice(),
                updatedArticle.getCategory().name(),
                updatedArticle.getState().name(),
                updatedArticle.getImage(),
                updatedArticle.getUserOffers().getIdUser(),
                updatedArticle.getPublished().toString());
    }

    @Override
    public ArticleResponseUpdateDetailDTO updateDescription(Integer id, ArticleDescriptionUpdateDTO dto) {
        Article article = articleRepository.findById(id).orElseThrow(() -> new RuntimeException("Article not found"));
        article.setDescription(dto.description());
        Article updatedArticle = articleRepository.save(article);
        return new ArticleResponseUpdateDetailDTO(
                updatedArticle.getIdArticle(),
                updatedArticle.getTitle(),
                updatedArticle.getDescription(),
                updatedArticle.getSize(),
                updatedArticle.getPrice(),
                updatedArticle.getCategory().name(),
                updatedArticle.getState().name(),
                updatedArticle.getImage(),
                updatedArticle.getUserOffers().getIdUser(),
                updatedArticle.getPublished().toString());
    }

    @Override
    public ArticleResponseUpdateDetailDTO updateSize(Integer id, ArticleSizeUpdateDTO dto) {
        Article article = articleRepository.findById(id).orElseThrow(() -> new RuntimeException("Article not found"));
        article.setSize(dto.size());
        Article updatedArticle = articleRepository.save(article);
        return new ArticleResponseUpdateDetailDTO(
                updatedArticle.getIdArticle(),
                updatedArticle.getTitle(),
                updatedArticle.getDescription(),
                updatedArticle.getSize(),
                updatedArticle.getPrice(),
                updatedArticle.getCategory().name(),
                updatedArticle.getState().name(),
                updatedArticle.getImage(),
                updatedArticle.getUserOffers().getIdUser(),
                updatedArticle.getPublished().toString());
    }

    @Override
    public ArticleResponseUpdateDetailDTO updatePrice(Integer id, ArticlePriceUpdateDTO dto) {
        Article article = articleRepository.findById(id).orElseThrow(() -> new RuntimeException("Article not found"));
        article.setPrice(dto.price());
        Article updatedArticle = articleRepository.save(article);
        return new ArticleResponseUpdateDetailDTO(
                updatedArticle.getIdArticle(),
                updatedArticle.getTitle(),
                updatedArticle.getDescription(),
                updatedArticle.getSize(),
                updatedArticle.getPrice(),
                updatedArticle.getCategory().name(),
                updatedArticle.getState().name(),
                updatedArticle.getImage(),
                updatedArticle.getUserOffers().getIdUser(),
                updatedArticle.getPublished().toString());
    }

    @Override
    public ArticleResponseUpdateDetailDTO updateState(Integer id, ArticleStateUpdateDTO dto) {
        Article article = articleRepository.findById(id).orElseThrow(() -> new RuntimeException("Article not found"));
        article.setState(dto.state());
        Article updatedArticle = articleRepository.save(article);
        return new ArticleResponseUpdateDetailDTO(
                updatedArticle.getIdArticle(),
                updatedArticle.getTitle(),
                updatedArticle.getDescription(),
                updatedArticle.getSize(),
                updatedArticle.getPrice(),
                updatedArticle.getCategory().name(),
                updatedArticle.getState().name(),
                updatedArticle.getImage(),
                updatedArticle.getUserOffers().getIdUser(),
                updatedArticle.getPublished().toString());
    }

    @Override
    public ArticleResponseUpdateDetailDTO updateCategory(Integer id, ArticleCategoryUpdateDTO dto) {
        Article article = articleRepository.findById(id).orElseThrow(() -> new RuntimeException("Article not found"));
        article.setCategory(dto.category());
        Article updatedArticle = articleRepository.save(article);
        return new ArticleResponseUpdateDetailDTO(
                updatedArticle.getIdArticle(),
                updatedArticle.getTitle(),
                updatedArticle.getDescription(),
                updatedArticle.getSize(),
                updatedArticle.getPrice(),
                updatedArticle.getCategory().name(),
                updatedArticle.getState().name(),
                updatedArticle.getImage(),
                updatedArticle.getUserOffers().getIdUser(),
                updatedArticle.getPublished().toString());
        }
        
    @Override
    public ArticleResponseDto getById(Integer id) {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Article not found"));

        LocalDateTime expiryDate = reserveRepository
                .findByArticleIdArticle(id)
                .map(Reserve::getExpiryDate)
                .orElse(null);

        Integer reservedByUserId = reserveRepository
                .findByArticleIdArticle(id)
                .map(r -> r.getUserWants().getIdUser())
                .orElse(null);

        return new ArticleResponseDto(
                article.getIdArticle(),
                article.getTitle(),
                article.getDescription(),
                article.getSize(),
                article.getPrice(),
                article.getCategory().name(),
                article.getState().name(),
                article.getImage(),
                article.getUserOffers().getIdUser(),
                article.getUserOffers().getUserName(),
                article.getIsReserved(),
                reservedByUserId,
                expiryDate);
    }
}
