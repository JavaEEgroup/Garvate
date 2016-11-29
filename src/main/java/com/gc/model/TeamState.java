package com.gc.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "team_state")
public class TeamState {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false)
    private Integer id;


    @Column(name = "description", nullable = false)
    private String description;

    @JoinColumn(name = "team_state_id")
    @OneToMany
    private List<TeamUser> teamUserList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<TeamUser> getTeamUserList() {
        return teamUserList;
    }

    public void setTeamUserList(List<TeamUser> teamUserList) {
        this.teamUserList = teamUserList;
    }
}
