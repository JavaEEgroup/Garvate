package com.gc.repository;

import com.gc.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional
public interface ArticleRepository extends JpaRepository<Article, Long> {

    List<Article> findAll();
}