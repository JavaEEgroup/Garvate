package com.gc.model;

import javax.persistence.*;

@Entity
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;


    @Column(name = "content", nullable = false)
    private  String content;


    @Column(name = "create_time")
    private String create_time;


    @JoinColumn(name = "to_user_id")
    @ManyToOne
    private User toUser;

    @JoinColumn(name = "from_user_id")
    @ManyToOne
    private User fromUser;

    @ManyToOne
    private Comment parent_comment;

    @JoinColumn(name = "article_id")
    @ManyToOne
    private Article article;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public User getToUser() {
        return toUser;
    }

    public void setToUser(User toUser) {
        this.toUser = toUser;
    }

    public User getFromUser() {
        return fromUser;
    }

    public void setFromUser(User fromUser) {
        this.fromUser = fromUser;
    }

    public Comment getParent_comment() {
        return parent_comment;
    }

    public void setParent_comment(Comment parent_comment) {
        this.parent_comment = parent_comment;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }
}
