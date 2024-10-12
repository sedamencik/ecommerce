package com.demo.ecommerce.repository;

import com.demo.ecommerce.entity.OrderItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItemEntity, Long> {
    List<OrderItemEntity> findAllByOrderEntityId(Long orderEntityId);
}
