package com.gc.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "credit_first_type")
public class CreditFirstType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false)
    private Long id;


    @Column(name = "description", nullable = false)
    private String description;

    @JoinColumn(name = "credit_first_type_id")
    @OneToMany
    private List<Credit> creditList;

    @JoinColumn(name = "credit_type_id")
    @OneToMany
    private List<CreditSecondType> creditSecondTypeList;

    @JoinColumn(name = "credit_type_id")
    @OneToMany
    private List<CreditThirdType> creditThirdTypeList;

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

    public List<CreditSecondType> getCreditSecondTypeList() {
        return creditSecondTypeList;
    }

    public void setCreditSecondTypeList(List<CreditSecondType> creditSecondTypeList) {
        this.creditSecondTypeList = creditSecondTypeList;
    }

    public List<CreditThirdType> getCreditThirdTypeList() {
        return creditThirdTypeList;
    }

    public void setCreditThirdTypeList(List<CreditThirdType> creditThirdTypeList) {
        this.creditThirdTypeList = creditThirdTypeList;
    }
}
