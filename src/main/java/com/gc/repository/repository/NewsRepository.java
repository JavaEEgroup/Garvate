package com.gc.repository.repository;

import com.gc.model.News;
import com.gc.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface NewsRepository extends JpaRepository<News,Long> {
    Page<News> findByUser(User user, Pageable pageable);
//    Page<News> findByUser(User user);
}
