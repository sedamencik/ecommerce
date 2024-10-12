package com.demo.ecommerce.dto;

import com.demo.ecommerce.entity.ProductEntity;

public class CartItemDTO {
    public ProductEntity productEntity;
    public Integer quantity;


    public CartItemDTO(ProductEntity productEntity, Integer quantity) {
        this.productEntity = productEntity;
        this.quantity = quantity;
    }

    public ProductEntity getProductEntity() {
        return productEntity;
    }

    public void setProductEntity(ProductEntity productEntity) {
        this.productEntity = productEntity;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
