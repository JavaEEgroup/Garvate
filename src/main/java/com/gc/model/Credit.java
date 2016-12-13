package com.gc.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "credit")
public class Credit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false)
    private Long id;


    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "create_credit_time",nullable = false)
    private Timestamp createCreditTime;

    @Column(name = "get_prize_time",nullable = false)
    private Timestamp getPrizeTime;

    @Column(name = "grade")
    private int grade;

    @Column(name = "value")
    private int value;

    @Column(name = "image_path")
    private String imagePath;

    @JoinColumn(name = "user_id")
    @ManyToOne
    private User user;

    @ManyToMany
    @JoinTable(
            name="credit_credit_status",
            inverseJoinColumns = @JoinColumn(name = "credit_status_id"),
            joinColumns = @JoinColumn(name = "credit_id"))
    private List<CreditStatus> creditStatusList;
}
