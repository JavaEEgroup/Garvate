package com.gc.model;

import javax.persistence.*;

@Entity
@Table(name = "team_user",uniqueConstraints = {
        @UniqueConstraint(columnNames = {"member_user_id","team_id"})
})
public class TeamUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false)
    private Long id;

    @JoinColumn(name="member_user_id")
    @ManyToOne
    private User member;

    @JoinColumn(name="team_id")
    @ManyToOne(cascade={CascadeType.REMOVE, CascadeType.ALL})
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

    public User getMember() {
        return member;
    }

    public void setMember(User member) {
        this.member = member;
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
