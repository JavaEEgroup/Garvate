package com.gc.model;

import javax.persistence.*;

/**
 * Created by zihe on 2016/11/24.
 */
@Entity
@Table(name = "tag_article")
public class TagArticle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false)
    private long id;
}
