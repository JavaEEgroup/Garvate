package com.gc.model;

import com.gc.Utils.Config;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "team")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "create_time",updatable = false)
    @CreationTimestamp
    private Timestamp createTime;

    @Column(name = "update_time")
    @UpdateTimestamp
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
    @OneToMany(cascade={CascadeType.REMOVE, CascadeType.ALL})
    private List<TeamUser> teamUserList;

    @OneToOne(cascade = CascadeType.ALL)
    private Project project;

    public Team(){

    }
    public Team(String desc, String title, int maxCount){
        this.description = desc;
        this.title = title;
        this.maxCount = maxCount;
//        this.createTime = new Timestamp(1);
//        this.updateTime = this.createTime;
    }

    public boolean hasUser(User user) {
        if(captain.getId().equals(user.getId())) return true;
        for(TeamUser teamUser : this.getTeamUserList()) {
            User user1 = teamUser.getMember();
            if(teamUser.getState().getDescription().equals(Config.TEAM_STATE_INTEAM)
                && user1.getId().equals(user.getId())) {
                return true;
            }
        }
        return false;
    }

    public int getCurrentCount() {
        int result = 1;
        if (this.teamUserList == null) {
            return result;
        }
        for (TeamUser teamUser : this.teamUserList) {
            if (teamUser.getState().getDescription().equals(Config.TEAM_STATE_INTEAM)) {
                ++result;
            }
        }
        return result;
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

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}

