package com.gc.model;

import javax.persistence.*;

@Entity
@Table(name = "vote_item")
public class VoteItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false)
    private long id;


    @Column(name = "title", nullable = false)
    private String title;


    @Column(name = "rank", nullable = false)
    private Integer rank;


    @JoinColumn(name = "vote_id")
    @ManyToOne
    private Vote vote;
}
