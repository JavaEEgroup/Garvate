package com.gc.model;

import javax.persistence.*;

@Entity
@Table(name = "team_tag")
public class TeamTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
