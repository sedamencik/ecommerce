package com.demo.ecommerce.repository;

import com.demo.ecommerce.entity.OrderEntity;
import com.demo.ecommerce.entity.OrderItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    List<OrderEntity> findAllByCustomerEntityId(Long customerEntityId);
    Optional<OrderEntity> findById(Long orderEntityId);



}
