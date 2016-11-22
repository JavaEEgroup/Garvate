package com.gc.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tag")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id", nullable = false)
    private long id;


    @Column(name = "description", nullable = false)
    private String description;


    @ManyToMany(targetEntity = User.class)
    private List userList;


    @ManyToMany(targetEntity = Article.class)
    private List articleList;
}
