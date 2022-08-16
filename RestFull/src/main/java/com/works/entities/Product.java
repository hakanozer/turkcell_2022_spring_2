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

    @Length(message = "title min 3 max 100,", min = 3, max = 100)
    @NotNull(message = "title NotNull")
    @NotEmpty(message = "title NotEmpty")
    @Column(length = 100)
    private String title;

    @Length(message = "detail min 3 max 100,", min = 3, max = 500)
    @NotNull(message = "detail NotNull")
    @NotEmpty(message = "detail NotEmpty")
    @Column(length = 500)
    private String detail;

    @Max(message = "price max 10000", value = 10000)
    @Min(message = "price min 1", value = 1)
    @NotNull(message = "price NotNull")
    private Integer price;

    @NotEmpty(message = "Email NotEmpty")
    @NotNull(message = "Email NotNull")
    @Length(message = "Email min 5 max 150", min = 5, max = 150)
    @Email(message = "Email format fail", regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$")
    @Column(length = 150)
    private String email;

}
