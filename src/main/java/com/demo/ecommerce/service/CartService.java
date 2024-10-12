package com.demo.ecommerce.service;

import com.demo.ecommerce.dto.CartDTO;
import org.springframework.stereotype.Service;

@Service
public interface CartService {
    //GetCart, UpdateCart, EmptyCart
    CartDTO getCart(Long customerId);
    //void updateCart(Long customerId, CartDTO cart);
    void emptyCart(Long customerId);
    CartDTO addProductToCart(Long customerId, Long productId, Integer quantity);
    CartDTO removeProductFromCart(Long customerId, Long productId);
}
