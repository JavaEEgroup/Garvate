package com.gc.model;

import javax.persistence.*;

@Entity
@Table(name = "article_tag")
public class ArticleTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false)
    private long id;
}
