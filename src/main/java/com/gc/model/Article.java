package com.gc.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


@Entity
@Table(name = "article")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;


    @Column(name = "title", nullable = false)
    private String title;


    @Column(name = "content", nullable = false)
    private String content;


    @Column(name = "create_time")
    private Timestamp create_time;


    @Column(name = "update_time")
    private Timestamp update_time;


    @Column(name = "view_count")
    private Integer view_count;


    @JoinColumn(name = "user_id")
    @ManyToOne
    private User user;


    @ManyToMany
    @JoinTable(
            name="article_tag",
            inverseJoinColumns = @JoinColumn(name = "tag_id"),
            joinColumns = @JoinColumn(name = "article_id"))
    private List<Tag> tagList;


    @OneToOne(cascade = CascadeType.ALL)
    private Vote vote;

    @JoinColumn(name = "article_id")
    @OneToMany
    private List<Comment> commentList;

    public Article() {

    }

    public Article(String title, String content, Timestamp create_time, List<Tag> tags) {
        this.title = title;
        this.content = content;
        this.create_time = create_time;
        this.update_time = create_time;
        this.view_count = 0;
        this.tagList = tags;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Timestamp create_time) {
        this.create_time = create_time;
    }

    public Timestamp getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Timestamp update_time) {
        this.update_time = update_time;
    }

    public Integer getView_count() {
        return view_count;
    }

    public void setView_count(Integer view_count) {
        this.view_count = view_count;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Tag> getTagList() {
        return tagList;
    }

    public void setTagList(List<Tag> tagList) {
        this.tagList = tagList;
    }

    public Vote getVote() {
        return vote;
    }

    public void setVote(Vote vote) {
        this.vote = vote;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    public void addView_Count() {
        this.view_count++;
    }
}
