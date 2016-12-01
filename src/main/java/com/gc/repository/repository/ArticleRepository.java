package com.gc.repository.repository;

import com.gc.model.Article;
import com.gc.repository.customerrespository.ArticleCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ArticleRepository extends JpaRepository<Article, Long>, ArticleCustomRepository {

}