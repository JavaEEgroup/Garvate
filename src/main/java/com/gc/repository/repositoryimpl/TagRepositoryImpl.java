package com.gc.repository.repositoryimpl;

import com.gc.model.Tag;
import com.gc.repository.customerrespository.TagCustomRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class TagRepositoryImpl implements TagCustomRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Tag> getTagsByTagIDs(List<Long> tagIDs) {
        try{
            List<Tag> tags = new ArrayList<>();
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Tag> query = builder.createQuery(Tag.class);
            Root<Tag> root = query.from(Tag.class);
            for (Long tagID : tagIDs) {
                query.where(builder.equal(root.get("id"),tagID));
                Tag tag = entityManager.createQuery(query).getResultList().get(0);
                tags.add(tag);
            }
            return tags;
        }catch (Exception exception){
            return null;
        }
    }
}
