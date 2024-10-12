package com.demo.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "customers")
@Getter
@Setter
public class Customer extends BaseEntity{

    private String name;
    private String email;

    /*
    //@PrimaryKeyJoinColumn
    @JsonManagedReference
    @JsonIgnore
    @OneToOne(mappedBy = "customerId", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    private Cart cart = new Cart(this.getId());*/

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Order> orders;


    // Getters and Setters

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }


    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }


}
