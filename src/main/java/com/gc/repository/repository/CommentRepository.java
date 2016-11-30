package com.gc.repository.repository;

import com.gc.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by jx-pc on 2016/11/30.
 */
public interface CommentRepository extends JpaRepository<Comment,Long> {

    List<Comment> findAll();
}
