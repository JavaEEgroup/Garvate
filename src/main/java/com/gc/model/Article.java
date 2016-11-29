package com.gc.model;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "article")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;


    @Column(name = "title", nullable = false)
    private String title;


    @Column(name = "content", nullable = false)
    private String content;


    @Column(name = "create_time")
    private String create_time;


    @Column(name = "update_time")
    private String update_time;


    @Column(name = "view_count")
    private String view_count;


    @JoinColumn(name = "user_id")
    @ManyToOne
    private User user;


    @ManyToMany
    @JoinTable(
            name="article_tag",
            inverseJoinColumns = @JoinColumn(name = "tag_id"),
            joinColumns = @JoinColumn(name = "article_id"))
    private List<Tag> tagList;


    @OneToOne
    private Vote vote;

    @JoinColumn(name = "article_id")
    @OneToMany
    private List<Comment> commentList;
}
