package com.swapstyle.swapstyle.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.swapstyle.swapstyle.dto.request.ArticleRequestDTO;
import com.swapstyle.swapstyle.entity.Article;
import com.swapstyle.swapstyle.entity.User;
import com.swapstyle.swapstyle.repository.ArticleRepository;
import com.swapstyle.swapstyle.dto.response.ArticleCardReponseDTO;
import com.swapstyle.swapstyle.dto.response.ArticleResponseDto;


@Service
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;

    private final UserService userService;

    public ArticleServiceImpl(ArticleRepository articleRepository, UserService userService) {
        this.articleRepository = articleRepository;
        this.userService = userService;
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
        return article.stream()
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

    // @Override
    // public Article getDetail(Integer id) {
    // // TODO Auto-generated method stub
    // throw new UnsupportedOperationException("Unimplemented method 'getDetail'");
    // }

    // @Override
    // public Article deleteArticle(Integer id) {
    // // TODO Auto-generated method stub
    // throw new UnsupportedOperationException("Unimplemented method
    // 'deleteArticle'");
    // }

    // @Override
    // public Article updateArticle(Article article) {
    // // TODO Auto-generated method stub
    // throw new UnsupportedOperationException("Unimplemented method
    // 'updateArticle'");
    // }

}
