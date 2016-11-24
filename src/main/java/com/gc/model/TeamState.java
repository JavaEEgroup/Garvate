package com.gc.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "team_state")
public class TeamState {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false)
    private long id;


    @Column(name = "description", nullable = false)
    private String description;

    @JoinColumn(name = "team_state_id")
    @OneToMany
    private List<TeamUser> teamUserList;
}
