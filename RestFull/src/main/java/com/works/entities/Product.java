package com.works.entities;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pid;

    @Length(message = "title min 0 max 2000,", min = 0, max = 2000)
    @NotNull(message = "title NotNull")
    @NotEmpty(message = "title NotEmpty")
    @Column(length = 2000)
    private String title;

    @Length(message = "detail min 0 max 5000,", min = 0, max = 5000)
    @NotNull(message = "detail NotNull")
    @NotEmpty(message = "detail NotEmpty")
    @Column(length = 5000)
    private String detail;

    @Max(message = "price max 1000000", value = 1000000)
    @Min(message = "price min 1", value = 1)
    @NotNull(message = "price NotNull")
    private Integer price;

    @NotEmpty(message = "Email NotEmpty")
    @NotNull(message = "Email NotNull")
    @Length(message = "Email min 0 max 550", min = 0, max = 550)
    @Email(message = "Email format fail")
    //@Email(message = "Email format fail", regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$")
    @Column(length = 550)
    private String email;

}
