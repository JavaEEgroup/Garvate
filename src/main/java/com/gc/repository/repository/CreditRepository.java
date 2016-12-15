package com.gc.repository.repository;


import com.gc.model.Credit;
import com.gc.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CreditRepository extends JpaRepository<Credit,Long> {
    List<Credit> findByUser(Pageable pageable, User user);
}
