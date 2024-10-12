package com.demo.ecommerce.dto;

import java.util.List;

public class CartDTO {
    //private Long id;
    private List<CartItemDTO> items;
    private Double amount;

    public CartDTO( List<CartItemDTO> cartItems) {
        this.items = cartItems;

        if (cartItems == null || cartItems.isEmpty()) {
            this.amount = 0.0;
            return;
        }
        this.amount = cartItems.stream().mapToDouble(item -> item.getProductEntity().getPrice() * item.getQuantity()).sum();
    }

    public List<CartItemDTO> getItems() {
        return items;
    }

    public void setItems(List<CartItemDTO> items) {
        this.items = items;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
