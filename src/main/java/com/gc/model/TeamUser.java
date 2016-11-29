package com.gc.model;

import javax.persistence.*;

@Entity
@Table(name = "team_user")
public class TeamUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false)
    private Long id;

    @JoinColumn(name="member_user_id")
    @ManyToOne
    private User memeber;

    @JoinColumn(name="team_id")
    @ManyToOne
    private Team team;

    @JoinColumn(name="team_state_id")
    @ManyToOne
    private TeamState state;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getMemeber() {
        return memeber;
    }

    public void setMemeber(User memeber) {
        this.memeber = memeber;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public TeamState getState() {
        return state;
    }

    public void setState(TeamState state) {
        this.state = state;
    }
}
