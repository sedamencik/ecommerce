package com.demo.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "carts")
@Getter
@Setter
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Cart extends BaseEntity {
    /*@OneToOne
    @JsonBackReference
    @JoinColumn(name = "customer_id", referencedColumnName = "id", nullable = false)
    private Customer customer;*/

    @Column(name = "customer_id", nullable = false)
    private Long customerId;

    @OneToMany(mappedBy = "cartId", cascade = CascadeType.ALL, orphanRemoval = true)
    //@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private List<CartItem> items = new ArrayList<>();

    private Double amount = 0.0;



    public Cart(Long customerId) {
        this.customerId = customerId;
    }

    public Cart() {

    }

    // Getters and Setters


    /*public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
        if (customer != null) {
            customer.setCart(this);
        }
    }*/

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }


}
