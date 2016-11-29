package com.gc.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by zihe on 2016/11/23.
 */
@Entity
@Table(name = "team")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;


//    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time", nullable = false)
    private Timestamp createTime;

//    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_time", nullable = false)
    private Timestamp updateTime;

    @Column(name = "max_count", nullable = false)
    private Integer maxCount;

    //true为开放 false为关闭
    @Column(name="state",nullable = false)
    private boolean state=true;

    @JoinColumn(name="captain_id",nullable = false)
    @ManyToOne
    private User captain;

    @ManyToMany
    @JoinTable(
            name="team_tag",
            inverseJoinColumns = @JoinColumn(name = "tag_id"),
            joinColumns = @JoinColumn(name = "team_id"))
    private List<Tag> tagList;

    @JoinColumn(name = "team_id")
    @OneToMany
    private List<TeamUser> teamUserList;

    public Team(){

    }
    public Team(String desc, String title, int maxCount,Timestamp createTime,Timestamp updateTime){
        this.description = desc;
        this.title = title;
        this.maxCount = maxCount;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getMaxCount() {
        return maxCount;
    }

    public void setMaxCount(Integer maxCount) {
        this.maxCount = maxCount;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public User getCaptain() {
        return captain;
    }

    public void setCaptain(User captain) {
        this.captain = captain;
    }

    public List<Tag> getTagList() {
        return tagList;
    }

    public void setTagList(List<Tag> tagList) {
        this.tagList = tagList;
    }

    public List<TeamUser> getTeamUserList() {
        return teamUserList;
    }

    public void setTeamUserList(List<TeamUser> teamUserList) {
        this.teamUserList = teamUserList;
    }
}

