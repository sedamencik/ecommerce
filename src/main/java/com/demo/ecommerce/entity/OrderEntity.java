package com.demo.ecommerce.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class OrderEntity extends BaseEntity{

    /*@ManyToOne
    @JoinColumn(name = "customer_entity_id", nullable = false)
    private CustomerEntity customerEntity;*/

    private Long customerEntityId;

    @OneToMany(mappedBy = "orderEntityId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItemEntity> orderItemEntities = new ArrayList<>();

    private Double amount;


    //Getter and Setter methods

    public Long getCustomerEntityId() {
        return customerEntityId;
    }

    public void setCustomerEntityId(Long customerEntityId) {
        this.customerEntityId = customerEntityId;
    }

    public List<OrderItemEntity> getOrderItemEntities() {
        return orderItemEntities;
    }

    public void setOrderItemEntities(List<OrderItemEntity> orderItemEntities) {
        this.orderItemEntities = orderItemEntities;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

}
