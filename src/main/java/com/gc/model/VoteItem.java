package com.gc.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "vote_item")
public class VoteItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false)
    private Long id;


    @Column(name = "title", nullable = false)
    private String title;


    @Column(name = "rank", nullable = false)
    private Integer rank;


    @JoinColumn(name = "vote_id")
    @ManyToOne
    private Vote vote;

    @ManyToMany
    @JoinTable(
            name="vote_item_user",
            inverseJoinColumns = @JoinColumn(name = "user_id"),
            joinColumns = @JoinColumn(name = "vote_item_id"))
    private List<User> userList;

    public VoteItem() {

    }

    public VoteItem(String title, Integer rank) {
        this.title = title;
        this.rank = rank;
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

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Vote getVote() {
        return vote;
    }

    public void setVote(Vote vote) {
        this.vote = vote;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }
}
