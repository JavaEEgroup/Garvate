package com.gc.repository;

import com.gc.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by zihe on 2016/11/28.
 */

@Transactional
public interface UserRepository extends JpaRepository<User,Long> {

}
