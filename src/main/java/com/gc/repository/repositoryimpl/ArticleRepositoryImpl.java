package com.gc.repository.repositoryimpl;

import com.gc.model.Article;
import com.gc.repository.customerrespository.ArticleCustomRepository;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import java.util.List;


public class ArticleRepositoryImpl implements ArticleCustomRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Article> findArticleByTagDescription(Pageable pageable, String tagDescription, String username, String key) {

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Article> query = builder.createQuery(Article.class);
        Root<Article> root = query.from(Article.class);
        
        return entityManager.createQuery(query.where(
                builder.or(
                        builder.equal(root.join("tagList", JoinType.LEFT).get("description"), tagDescription),
                        builder.equal(root.join("user", JoinType.LEFT).get("username"), username),
                        builder.like(root.get("title"), "%" + key + "%"),
                        builder.like(root.get("content"), "%" + key + "%")
                )
        ).distinct(true))
                .setFirstResult(pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .getResultList();
    }
}
