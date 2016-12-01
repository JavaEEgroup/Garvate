package com.gc.repository.customerrespository;


import com.gc.model.Article;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ArticleCustomRepository {
    List<Article> findArticleByTagDescription(Pageable pageable, String tagDescription, String username, String key);
}
