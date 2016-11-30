package com.gc.model;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "vote")
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false)
    private Long id;


    @Column(name = "title", nullable = false)
    private String title;


    @Column(name = "description")
    private String description;


    @Column(name = "max", nullable = false)
    private Integer max;


    @Column(name = "min", nullable = false)
    private Integer min;


    @OneToOne(mappedBy = "vote")
    private Article article;

//    @JoinColumn(name = "vote_id")
//    @OneToMany
    @JoinColumn(name = "vote_id")
    @OneToMany(cascade = CascadeType.ALL)
    private List<VoteItem> voteItemList;

    public Vote() {

    }

    public Vote(String title, String description, Integer max, Integer min) {
        this.title = title;
        this.description = description;
        this.max = max;
        this.min = min;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getMax() {
        return max;
    }

    public void setMax(Integer max) {
        this.max = max;
    }

    public Integer getMin() {
        return min;
    }

    public void setMin(Integer min) {
        this.min = min;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public List<VoteItem> getVoteItemList() {
        return voteItemList;
    }

    public void setVoteItemList(List<VoteItem> voteItemList) {
        this.voteItemList = voteItemList;
    }
}
