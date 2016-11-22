package com.gc.model;

import javax.persistence.*;

@Entity
@Table(name = "vote_item")
public class VoteItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id", nullable = false)
    private long id;


    @Column(name = "title", nullable = false)
    private String title;


    @Column(name = "rank", nullable = false)
    private int rank;


    @ManyToOne
    private Vote vote;
}
