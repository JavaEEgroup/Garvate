package com.gc.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false)
    private long id;


    @Column(name = "account", nullable = false)
    private String account;


    @Column(name = "username", nullable = false)
    private String username;


    @Column(name = "password", nullable = false)
    private String password;


    @Column(name = "phone")
    private String phone;


    @Column(name = "email")
    private String email;


    @Column(name = "type")
    private int type;


    @ManyToOne
    private Major major;


    @ManyToMany(mappedBy = "userList")
    private List<Tag> tagList;

    @JoinColumn(name = "captain_id")
    @OneToMany
    private List<Team> captainTeamList;

    @JoinColumn(name = "member_user_id")
    @OneToMany
    private List<TeamUser> teamUserList;

    @JoinColumn(name = "user_id")
    @OneToMany
    private List<Article> articleList;

    @JoinColumn(name = "to_user_id")
    @OneToMany
    private List<Comment> toCommentList;

    @JoinColumn(name = "from_user_id")
    @OneToMany
    private List<Comment> fromCommentList;
}
