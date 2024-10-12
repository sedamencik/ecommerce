package com.demo.ecommerce.dto;

import com.demo.ecommerce.entity.OrderItem;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;

import java.util.List;

public class OrderDTO {
    private Integer customerId;
    private List<OrderItem> orderItems;
    private Double totalAmount;
}
