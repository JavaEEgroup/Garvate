package com.gc.model;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "article")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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


    @ManyToOne
    private User user;


    @ManyToMany(targetEntity = Tag.class)
    private List tagList;


    @OneToOne
    private Vote vote;
}
