package com.gc.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "credit_second_type")
public class CreditSecondType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false)
    private Long id;


    @Column(name = "description", nullable = false)
    private String description;

    @JoinColumn(name = "credit_second_type_id")
    @OneToMany
    private List<Credit> creditList;

    @JoinColumn(name = "credit_type_id")
    @ManyToOne
    private CreditFirstType creditFirstType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Credit> getCreditList() {
        return creditList;
    }

    public void setCreditList(List<Credit> creditList) {
        this.creditList = creditList;
    }

    public CreditFirstType getCreditFirstType() {
        return creditFirstType;
    }

    public void setCreditFirstType(CreditFirstType creditFirstType) {
        this.creditFirstType = creditFirstType;
    }
}
