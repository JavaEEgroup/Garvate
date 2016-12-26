package com.gc.repository.repository;

import com.gc.model.News;
import org.springframework.data.jpa.repository.JpaRepository;


public interface NewsRepository extends JpaRepository<News,Long> {
}
