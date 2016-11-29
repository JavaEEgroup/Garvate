package com.gc.repository.repository;

import com.gc.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface UserRepository extends JpaRepository<User,Long> {
    User findByAccount(String account);
}
