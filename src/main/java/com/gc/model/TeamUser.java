package com.gc.model;

import javax.persistence.*;

@Entity
@Table(name = "team_user")
public class TeamUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false)
    private long id;

    @JoinColumn(name="member_user_id")
    @ManyToOne
    private User memeber;

    @JoinColumn(name="team_id")
    @ManyToOne
    private Team team;

    @JoinColumn(name="team_state_id")
    @ManyToOne
    private TeamState state;
}
