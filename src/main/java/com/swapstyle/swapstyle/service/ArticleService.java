package com.swapstyle.swapstyle.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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

public interface ArticleService {

public ArticleResponseDto createArticle(ArticleRequestDTO dto, Integer idUser );

public List<ArticleCardReponseDTO> getAllArticles();

// public Article getDetail ( Integer id);

// public Article deleArticle(Integer id);

// public Article deleteArticle(Integer id);

public List<ArticleCardReponseDTO> findByCategory(Category category);

public List<ArticleCardReponseDTO> findByPublishedRange(PublishedRange range);

 public Page<ArticleCardReponseDTO> getArticlesGallery(Pageable pageable);

 public List<ArticleCardReponseDTO> getArticlesByUser(Integer idUser);

 public ArticleResponseUpdateDetailDTO updateTitle(Integer id, ArticleTitleUpdateDTO dto);

public ArticleResponseUpdateDetailDTO updateDescription(Integer id, ArticleDescriptionUpdateDTO dto);

public ArticleResponseUpdateDetailDTO updateSize(Integer id, ArticleSizeUpdateDTO dto);

public ArticleResponseUpdateDetailDTO updatePrice(Integer id, ArticlePriceUpdateDTO dto);

public ArticleResponseUpdateDetailDTO updateState(Integer id, ArticleStateUpdateDTO
     dto);

public ArticleResponseUpdateDetailDTO updateCategory(Integer id, ArticleCategoryUpdateDTO dto);
}
