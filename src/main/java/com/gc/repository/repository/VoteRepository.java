package com.gc.repository.repository;

import com.gc.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;


@Transactional
public interface VoteRepository extends JpaRepository<Vote, Long> {
}
