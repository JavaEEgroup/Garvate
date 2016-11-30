package com.gc.ViewModel.community;

/**
 * Created by jx-pc on 2016/11/30.
 */
public class CommunityAdd {

    private int state;
    private Long article_id;

    public CommunityAdd(int state, Long article_id) {
        this.state = state;
        this.article_id = article_id;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Long getArticle_id() {
        return article_id;
    }

    public void setArticle_id(Long article_id) {
        this.article_id = article_id;
    }
}
