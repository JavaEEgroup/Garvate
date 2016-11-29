package com.gc.model;

import javax.persistence.*;

/**
 * Created by zihe on 2016/11/24.
 */
@Entity
@Table(name = "user_tag")
public class UserTag {
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
