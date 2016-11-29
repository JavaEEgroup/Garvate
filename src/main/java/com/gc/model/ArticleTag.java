package com.gc.model;

import javax.persistence.*;

@Entity
@Table(name = "article_tag")
public class ArticleTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false)
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
