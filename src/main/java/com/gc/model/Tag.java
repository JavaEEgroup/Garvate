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

    @ManyToMany(mappedBy = "tagList")
    private List<User> userList;


    @ManyToMany(mappedBy = "tagList")
    private List<Article> articleList;

    @ManyToMany(mappedBy = "tagList")
    private List<Team> teamList;
}
