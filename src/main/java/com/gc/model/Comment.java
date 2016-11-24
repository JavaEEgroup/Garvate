package com.gc.model;

import javax.persistence.*;

@Entity
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;


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
}
