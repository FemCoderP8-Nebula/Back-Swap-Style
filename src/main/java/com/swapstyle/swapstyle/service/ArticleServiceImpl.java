package com.swapstyle.swapstyle.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.swapstyle.swapstyle.dto.request.ArticleRequestDTO;
import com.swapstyle.swapstyle.entity.Article;
import com.swapstyle.swapstyle.repository.ArticleRepository;

import lombok.AllArgsConstructor;

import com.swapstyle.swapstyle.dto.response.ArticleCardReponseDTO;

@AllArgsConstructor
@Service
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;
    // private final UserService userService;

     

    @Override
    public Article createArticle(ArticleRequestDTO dto) {
        Article article = new Article();
        article.setTitle(dto.title());
        article.setDescription(dto.description());
        article.setSize(dto.size());
        article.setPrice(dto.price());
        article.setCategory(dto.category());
        article.setState(dto.state());
        article.setImage(dto.image());

        return articleRepository.save(article);

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


    // @Override
    // public Article getDetail(Integer id) {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'getDetail'");
    // }

    // @Override
    // public Article deleteArticle(Integer id) {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'deleteArticle'");
    // }

    // @Override
    // public Article updateArticle(Article article) {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'updateArticle'");
    // }

    
}
 
