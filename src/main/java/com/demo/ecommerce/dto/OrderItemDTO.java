package com.demo.ecommerce.dto;

import jakarta.persistence.Column;

public class OrderItemDTO {
    private Long productEntityId;

    private Double priceAtOrderTime;

    private Integer quantity;




    // Getters and Setters

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
