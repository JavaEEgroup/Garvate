package com.gc.repository.repositoryimpl;

import com.gc.model.Tag;
import com.gc.model.Team;
import com.gc.model.TeamTag;
import com.gc.repository.customerrespository.TeamCustomRepository;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.List;

public class TeamRepositoryImpl implements TeamCustomRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Team> findTeamsByTagDescription(Pageable pageable, String tagDescription, String username, String key) {

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Team> query = builder.createQuery(Team.class);
        Root<Team> root = query.from(Team.class);

        return entityManager.createQuery(query.where(
                builder.or(
                        builder.equal(root.join("tagList",JoinType.LEFT).get("description"),tagDescription),
                        builder.equal(root.join("teamUserList", JoinType.LEFT).join("member", JoinType.LEFT).get("username"), username),
                        builder.equal(root.join("captain", JoinType.LEFT).get("username"), username),
                        builder.like(root.get("title"), "%" + key + "%"),
                        builder.like(root.get("description"), "%" + key + "%")
                )
        ).distinct(true))
                .setFirstResult(pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .getResultList();
    }

}
