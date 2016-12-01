package com.gc.ViewModel.community;

/**
 * Created by jx-pc on 2016/11/30.
 */
public class CommunityAdd {

    private int status;
    private Long article_id;

    public CommunityAdd(int state, Long article_id) {
        this.status = state;
        this.article_id = article_id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Long getArticle_id() {
        return article_id;
    }

    public void setArticle_id(Long article_id) {
        this.article_id = article_id;
    }
}
