package com.demo.ecommerce.dto;

import com.demo.ecommerce.entity.OrderItemEntity;

import java.util.List;

public class OrderDTO {
    private Integer customerId;
    private List<OrderItemEntity> orderItemEntities;
    private Double totalAmount;
}
