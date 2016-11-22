package com.gc.model;


import javax.persistence.*;

@Entity
@Table(name = "vote")
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id", nullable = false)
    private long id;


    @Column(name = "title", nullable = false)
    private String title;


    @Column(name = "description")
    private String description;


    @Column(name = "max", nullable = false)
    private int max;


    @Column(name = "min", nullable = false)
    private int min;


    @OneToOne
    private Article article;
}
