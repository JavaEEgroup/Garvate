package com.gc.repository.customerrespository;

import com.gc.model.Tag;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

public interface TagCustomRepository {
    List<Tag> getTagsByTagIDs(List<Long> tagIDs);
}
