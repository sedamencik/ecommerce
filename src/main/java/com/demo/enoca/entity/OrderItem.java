package com.demo.enoca.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ordered_products")
@Getter
@Setter
public class OrderItem extends BaseEntity{
    // Bir sipariş öğesi bir siparişe aittir
    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    private Integer productId;

    // Sipariş anındaki ürün fiyatı
    @Column(nullable = false)
    private Double priceAtOrderTime;

    // Sipariş edilen miktar
    @Column(nullable = false)
    private Integer quantity;
}
