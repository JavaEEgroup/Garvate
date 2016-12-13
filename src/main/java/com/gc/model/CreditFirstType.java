package com.gc.model;

import javax.persistence.*;

@Entity
@Table(name = "credit_first_type")
public class CreditFirstType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false)
    private Long id;


    @Column(name = "description", nullable = false)
    private String description;
}
