package com.demo.ecommerce.repository;

import com.demo.ecommerce.entity.Cart;
import com.demo.ecommerce.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem,Long> {
    Optional<CartItem> findByCartIdAndProductId(Long cartId, Long productId);
    Optional<List<CartItem>> findByCartId(Long cartId);
}
