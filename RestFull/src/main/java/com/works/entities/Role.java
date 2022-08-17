package com.works.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer rid;

    private String role;

    @ManyToMany(mappedBy = "roles")
    @JsonIgnore
    List<Admin> admins;

}
