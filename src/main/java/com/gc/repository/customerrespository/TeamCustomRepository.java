package com.gc.repository.customerrespository;


import com.gc.model.Team;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TeamCustomRepository {
    List<Team> findTeamsByTagDescription(Pageable pageable, String tagDescription, String username, String key);
}
