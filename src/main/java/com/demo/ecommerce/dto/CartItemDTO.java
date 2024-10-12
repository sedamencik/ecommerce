package com.demo.ecommerce.dto;

import com.demo.ecommerce.entity.Product;

public class CartItemDTO {
    public Product product;
    public Integer quantity;


    public CartItemDTO(Product product, Integer quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
