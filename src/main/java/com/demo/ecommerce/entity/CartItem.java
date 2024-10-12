package com.demo.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class CartItem extends BaseEntity{
    /*@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id", nullable = false)
    @JsonBackReference
    private Cart cart;*/

    private Long cartId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
    private int quantity;

    public CartItem() {
    }

    public CartItem(Product product, int quantity, Double price) {
        this.product = product;
        this.quantity = quantity;
    }



    public Long getCartId() {
        return cartId;
    }

    public void setCart(Long cart) {
        this.cartId = cart;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    public Double getTotalPrice() {
        return product.getPrice()*quantity;
    }}
