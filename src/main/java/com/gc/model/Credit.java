package com.gc.model;

import org.hibernate.annotations.CreationTimestamp;

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

    @Column(name = "create_credit_time",updatable = false)
    @CreationTimestamp
    private Timestamp createCreditTime;


    @Column(name = "get_prize_time",nullable = false)
    private Timestamp getPrizeTime;

    @Column(name = "grade")
    private int grade=0;

    @Column(name = "value")
    private int value=0;

    @Column(name = "image_path")
    private String imagePath;

    @JoinColumn(name = "user_id")
    @ManyToOne
    private User user;

    @JoinColumn(name = "credit_status_id")
    @ManyToOne
    private CreditStatus creditStatus;

    @JoinColumn(name = "credit_first_type_id")
    @ManyToOne
    private CreditFirstType creditFirstType;

    @JoinColumn(name = "credit_second_type_id")
    @ManyToOne
    private CreditSecondType creditSecondType;

    @JoinColumn(name = "credit_third_type_id")
    @ManyToOne
    private CreditThirdType creditThirdType;

    public Credit(User user,
                  String name,Timestamp getPrizeTime,
                  CreditFirstType firstType,CreditSecondType secondType,CreditThirdType thirdType){
        this.user = user;
        this.name = name;
        this.getPrizeTime = getPrizeTime;
        this.creditFirstType = firstType;
        this.creditSecondType = secondType;
        this.creditThirdType = thirdType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getCreateCreditTime() {
        return createCreditTime;
    }

    public void setCreateCreditTime(Timestamp createCreditTime) {
        this.createCreditTime = createCreditTime;
    }

    public Timestamp getGetPrizeTime() {
        return getPrizeTime;
    }

    public void setGetPrizeTime(Timestamp getPrizeTime) {
        this.getPrizeTime = getPrizeTime;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public CreditStatus getCreditStatus() {
        return creditStatus;
    }

    public void setCreditStatus(CreditStatus creditStatusList) {
        this.creditStatus = creditStatusList;
    }

    public CreditFirstType getCreditFirstType() {
        return creditFirstType;
    }

    public void setCreditFirstType(CreditFirstType creditFirstType) {
        this.creditFirstType = creditFirstType;
    }

    public CreditSecondType getCreditSecondType() {
        return creditSecondType;
    }

    public void setCreditSecondType(CreditSecondType creditSecondType) {
        this.creditSecondType = creditSecondType;
    }

    public CreditThirdType getCreditThirdType() {
        return creditThirdType;
    }

    public void setCreditThirdType(CreditThirdType creditThirdType) {
        this.creditThirdType = creditThirdType;
    }
}
