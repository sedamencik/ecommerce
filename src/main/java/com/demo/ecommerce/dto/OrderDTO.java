package com.demo.ecommerce.dto;

import com.demo.ecommerce.entity.OrderItemEntity;

import java.util.List;

public class OrderDTO {
    private Long customerId;
    private List<OrderItemDTO> orderItemDTOs;
    private Double amount;







    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public List<OrderItemDTO> getOrderItemEntities() {
        return orderItemDTOs;
    }

    public void setOrderItemEntities(List<OrderItemDTO> orderItemEntities) {
        this.orderItemDTOs = orderItemEntities;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
