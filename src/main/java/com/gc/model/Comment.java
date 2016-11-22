package com.gc.model;

import javax.persistence.*;

@Entity
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private long id;


    @Column(name = "content", nullable = false)
    private  String content;


    @Column(name = "create_time")
    private String create_time;


    @ManyToOne
    private User toUser;


    @ManyToOne
    private User fromUser;


    @ManyToOne
    private Comment parent_comment;


    @ManyToOne
    private Article article;
}
