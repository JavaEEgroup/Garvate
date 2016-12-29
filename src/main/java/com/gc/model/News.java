package com.gc.model;


import com.gc.Utils.Utils;

import javax.persistence.*;
import java.sql.Timestamp;


@Entity
@Table(name = "news")
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "visible", nullable = false)
    private boolean visible;

    @Column(name = "create_time")
    private Timestamp create_time;

    @Column(name = "url")
    private String url;

    @JoinColumn(name = "user_id")
    @ManyToOne
    private User user;

    public News() {
    }

    public News(String title, String content, String url, User user) {
        this.title = title;
        this.content = content;
        this.visible = true;
        this.url = url;
        this.user = user;
        this.create_time = Utils.getCurrentTime();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public Timestamp getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Timestamp create_time) {
        this.create_time = create_time;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
