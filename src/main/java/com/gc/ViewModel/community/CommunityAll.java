package com.gc.ViewModel.community;

import com.gc.model.Article;
import com.gc.model.User;
import com.gc.model.Vote;

import java.sql.Timestamp;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;


public class CommunityAll {

    private int state;

    private ArrayList<CommunityAllChild> results;

    public CommunityAll(int state) {
        this.state = state;
        this.results = new ArrayList<>();
    }

    public ArrayList<CommunityAllChild> getResults() {
        return results;
    }

    public void add2Results(List<Article> articles) {
        for(Article article : articles) {
            results.add(new CommunityAllChild(article));
        }
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}

class CommunityAllChild {

    private String title;
    private Long id;
    private Long user_id;
    private String user_name;
    private Integer view_count;
    private Long vote_id;
    private AbstractList<String> tag;
    private Timestamp create_time;
    private Timestamp update_time;

    public CommunityAllChild(Article article) {

        User user = article.getUser();
        this.title = article.getTitle();
        this.id = article.getId();
        this.user_id = user.getId();
        this.user_name = user.getUsername();
        this.view_count = article.getView_count();
        Vote vote = article.getVote();
        if(vote != null) this.vote_id = vote.getId();
        this.create_time = article.getCreate_time();
        this.update_time = article.getUpdate_time();
    }

    public Timestamp getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Timestamp update_time) {
        this.update_time = update_time;
    }

    public Timestamp getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Timestamp create_time) {
        this.create_time = create_time;
    }

    public AbstractList<String> getTag() {
        return tag;
    }

    public void setTag(AbstractList<String> tag) {
        this.tag = tag;
    }

    public Long getVote_id() {
        return vote_id;
    }

    public void setVote_id(Long vote_id) {
        this.vote_id = vote_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public Integer getView_count() {
        return view_count;
    }

    public void setView_count(Integer view_count) {
        this.view_count = view_count;
    }
}