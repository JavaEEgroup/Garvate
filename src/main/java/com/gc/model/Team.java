package com.gc.model;

import javax.persistence.*;
import java.sql.Date;
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
    private long id;

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
    private int maxCount;

    //true为开放 false为关闭
    @Column(name="state",nullable = false)
    private boolean state=true;

    @JoinColumn(name="captain_id")
    @ManyToOne
    private User captain;

    @ManyToMany(mappedBy = "teamList")
    private List<Tag> tagList;

    @JoinColumn(name = "team_id")
    @OneToMany
    private List<TeamUser> teamUserList;
}

