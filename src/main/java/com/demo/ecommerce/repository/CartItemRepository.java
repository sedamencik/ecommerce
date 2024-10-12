package com.demo.ecommerce.repository;

import com.demo.ecommerce.entity.CartItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItemEntity,Long> {
    Optional<CartItemEntity> findByCartIdAndProductEntityId(Long cartId, Long productEntityId);
    Optional<List<CartItemEntity>> findByCartId(Long cartId);
}
