package com.gc.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tag")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false)
    private long id;


    @Column(name = "description", nullable = false)
    private String description;


//    @ManyToMany(targetEntity = User.class)
//    private List userList;

    @ManyToMany
    @JoinTable(
            name="tag_user",
            inverseJoinColumns = @JoinColumn(name = "user_id"),
            joinColumns = @JoinColumn(name = "tag_id"))
    private List<User> userList;

//    @ManyToMany(targetEntity = Article.class)
//    private List articleList;

    @ManyToMany
    @JoinTable(
            name="tag_article",
            inverseJoinColumns = @JoinColumn(name = "article_id"),
            joinColumns = @JoinColumn(name = "tag_id"))
    private List<Article> articleList;

//    @ManyToMany(targetEntity = Team.class)
//    private List teamList;
    @ManyToMany
    @JoinTable(
            name="tag_team",
            inverseJoinColumns = @JoinColumn(name = "team_id"),
            joinColumns = @JoinColumn(name = "tag_id"))
    private List<Team> teamList;
}
