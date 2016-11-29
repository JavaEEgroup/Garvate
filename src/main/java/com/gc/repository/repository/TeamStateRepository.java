package com.gc.repository.repository;


import com.gc.model.TeamState;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamStateRepository extends JpaRepository<TeamState, Long> {
    TeamState getByDescription(String description);
}
