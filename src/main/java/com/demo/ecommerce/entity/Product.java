package com.demo.ecommerce.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "products")
@Getter
@Setter
public class Product extends BaseEntity{
    @Column(nullable = false)
    private String name;

    private Double price;

    @Column(nullable = false)
    private Integer stock;
}
