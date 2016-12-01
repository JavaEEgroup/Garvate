package com.gc.repository.repository;

import com.gc.model.Team;
import com.gc.model.TeamUser;
import com.gc.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamUserRepository extends JpaRepository<TeamUser,Long>{
    TeamUser findByMemberAndTeam(User member, Team team);
}
