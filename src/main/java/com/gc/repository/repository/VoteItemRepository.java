package com.gc.repository.repository;

import com.gc.model.VoteItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface VoteItemRepository extends JpaRepository<VoteItem, Long> {
}
