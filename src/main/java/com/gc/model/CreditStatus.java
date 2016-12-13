package com.gc.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "credit_status")
public class CreditStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false)
    private Long id;


    @Column(name = "description", nullable = false)
    private String description;

    @ManyToMany(mappedBy = "creditStatusList")
    private List<Credit> creditList;
}
