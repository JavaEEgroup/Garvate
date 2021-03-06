package com.gc.repository.repository;

import com.gc.model.Team;
import com.gc.model.User;
import com.gc.repository.customerrespository.TeamCustomRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team, Long>, TeamCustomRepository {
    //    List<Team> findByTitleLikeOrDescriptionLikeOrCaptainLike
    Page<Team> findByCaptain(Pageable pageable, User captain);
}
