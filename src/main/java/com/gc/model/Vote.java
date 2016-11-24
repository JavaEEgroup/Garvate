package com.gc.model;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "vote")
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @JoinColumn(name = "vote_id")
    @OneToMany
    private List<VoteItem> voteItemList;
}
