package com.works.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cid;

    @Column(length = 100)
    private String name;

    @Column(unique = true)
    private String email;

    @Column(length = 32)
    private String password;

}
