package com.demo.ecommerce.dto;

import com.demo.ecommerce.entity.CartItem;
import com.demo.ecommerce.entity.Product;

import java.util.List;

public class CartDTO {
    //private Long id;
    private List<CartItemDTO> items;
    private Double totalAmount;

    public CartDTO( List<CartItemDTO> cartItems) {
        this.items = cartItems;

        if (cartItems == null || cartItems.isEmpty()) {
            this.totalAmount = 0.0;
            return;
        }
        this.totalAmount = cartItems.stream().mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity()).sum();
    }

    public List<CartItemDTO> getItems() {
        return items;
    }

    public void setItems(List<CartItemDTO> items) {
        this.items = items;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }
}
