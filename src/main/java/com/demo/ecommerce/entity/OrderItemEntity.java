package com.demo.ecommerce.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ordered_products")
@Getter
@Setter
public class OrderItemEntity extends BaseEntity{
    // Bir sipariş öğesi bir siparişe aittir
    /*@ManyToOne
    @JoinColumn(name = "order_entity_id", nullable = false)
    private OrderEntity orderEntity;*/

    private Long orderEntityId;

    private Long productEntityId;

    // Sipariş anındaki ürün fiyatı
    //@Column(nullable = false)
    private Double priceAtOrderTime;

    // Sipariş edilen miktar
    @Column(nullable = false)
    private Integer quantity;


    //Getter and Setter methods

    public Long getOrderEntityId() {
        return orderEntityId;
    }

    public void setOrderEntityId(Long orderEntityId) {
        this.orderEntityId = orderEntityId;
    }

    public Long getProductEntityId() {
        return productEntityId;
    }

    public void setProductEntityId(Long productEntityId) {
        this.productEntityId = productEntityId;
    }

    public Double getPriceAtOrderTime() {
        return priceAtOrderTime;
    }

    public void setPriceAtOrderTime(Double priceAtOrderTime) {
        this.priceAtOrderTime = priceAtOrderTime;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
