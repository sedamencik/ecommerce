package com.demo.ecommerce.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class CartItemEntity extends BaseEntity{
    private Long cartId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_entity_id", nullable = false)
    private ProductEntity productEntity;
    private int quantity;



    public CartItemEntity() {
    }

    public CartItemEntity(ProductEntity productEntity, int quantity, Double price) {
        this.productEntity = productEntity;
        this.quantity = quantity;
    }



    public Long getCartId() {
        return cartId;
    }

    public void setCart(Long cart) {
        this.cartId = cart;
    }

    public ProductEntity getProductEntity() {
        return productEntity;
    }

    public void setProductEntity(ProductEntity productEntity) {
        this.productEntity = productEntity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    public Double getTotalPrice() {
        return productEntity.getPrice()*quantity;
    }}
