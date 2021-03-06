package com.gc.repository.repository;

import com.gc.model.VoteItemUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;


@Transactional
public interface VoteItemUserRepository extends JpaRepository<VoteItemUser, Long> {

}
