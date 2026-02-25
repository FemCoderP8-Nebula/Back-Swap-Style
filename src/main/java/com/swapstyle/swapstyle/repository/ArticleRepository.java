package com.swapstyle.swapstyle.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.swapstyle.swapstyle.entity.Article;

public interface ArticleRepository  extends JpaRepository <Article, Integer> {

}