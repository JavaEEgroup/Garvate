package com.gc.model;

import javax.persistence.*;


@Entity
@Table(name = "major")
public class Major {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id", nullable = false)
    private long id;


    @Column(name = "description", nullable = false)
    private String description;
}
