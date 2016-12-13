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
}
