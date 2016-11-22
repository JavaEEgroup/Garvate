package com.gc.model;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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


    @ManyToMany(targetEntity = Tag.class)
    private List tagList;
}
