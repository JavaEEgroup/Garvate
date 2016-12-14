package com.gc.repository.customerrespository;


import com.gc.model.Team;
import com.gc.model.User;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TeamCustomRepository {
    List<Team> findTeamsByTagDescription(Pageable pageable, String tagDescription, String username, String key);
    List<Team> findTeamsByUser(Pageable pageable, User user);
}
