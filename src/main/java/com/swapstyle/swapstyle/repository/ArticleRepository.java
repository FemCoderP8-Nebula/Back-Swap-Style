package com.swapstyle.swapstyle.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.swapstyle.swapstyle.entity.Article;
import java.util.List;
import com.swapstyle.swapstyle.entity.enums.Category;


public interface ArticleRepository  extends JpaRepository <Article, Integer> {
    List<Article> findByCategory(Category category);
}