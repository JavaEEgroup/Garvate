package com.gc.repository.repository;

import com.gc.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by jx-pc on 2016/11/30.
 */
public interface VoteRepository extends JpaRepository<Vote, Long> {
}
