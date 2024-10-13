package com.demo.ecommerce.service.impl;

import com.demo.ecommerce.dto.OrderDTO;
import com.demo.ecommerce.dto.OrderItemDTO;
import com.demo.ecommerce.entity.CartEntity;
import com.demo.ecommerce.entity.OrderEntity;
import com.demo.ecommerce.entity.OrderItemEntity;
import com.demo.ecommerce.mapper.OrderItemMapper;
import com.demo.ecommerce.mapper.OrderMapper;
import com.demo.ecommerce.repository.CartRepository;
import com.demo.ecommerce.repository.OrderItemRepository;
import com.demo.ecommerce.repository.OrderRepository;
import com.demo.ecommerce.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.data.repository.util.ClassUtils.ifPresent;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final CartRepository cartRepository;

    private final OrderItemMapper orderItemMapper;
    private final OrderMapper orderMapper;


    public OrderServiceImpl(OrderRepository orderRepository, OrderItemRepository orderItemRepository, CartRepository cartRepository, OrderItemMapper orderItemMapper, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.cartRepository = cartRepository;
        this.orderItemMapper = orderItemMapper;
        this.orderMapper = orderMapper;
    }

    @Override
    public void placeOrder(Long customerId) {
        OrderEntity orderEntity = new OrderEntity();

        cartRepository.findByCustomerId(customerId)
                .ifPresent(cartEntity -> {

                    orderEntity.setCustomerEntityId(cartEntity.getCustomerId());
                    orderEntity.setAmount(cartEntity.getAmount());

                    // OrderEntity'yi veritabanına kaydeder ve ID'sinin oluşmasını sağlar
                    orderRepository.save(orderEntity);

                    cartEntity.getItems().forEach(cartItemEntity -> {
                        OrderItemEntity orderItemEntity = new OrderItemEntity();

                        orderItemEntity.setProductEntityId(cartItemEntity.getProductEntity().getId());
                        orderItemEntity.setOrderEntityId(orderEntity.getId());
                        orderItemEntity.setQuantity(cartItemEntity.getQuantity());
                        orderItemEntity.setPriceAtOrderTime(cartItemEntity.getTotalPrice() / cartItemEntity.getQuantity());
                        orderEntity.getOrderItemEntities().add(orderItemEntity);

                        orderItemRepository.save(orderItemEntity);

                    });

                });

        if (orderEntity.getOrderItemEntities().isEmpty()) {
            throw new RuntimeException("Cart is empty");
        }
        orderRepository.save(orderEntity);
    }

    @Override
    public OrderDTO getOrderForCode(Long orderId) {

        List<OrderItemDTO> orderItemDTOs = new ArrayList<>();
        OrderDTO orderDTO = new OrderDTO();

        orderRepository.findById(orderId).ifPresent(orderEntity -> {
            orderItemRepository.findByOrderEntityId(orderEntity.getId()).forEach(orderItemEntity -> {
                OrderItemDTO orderItemDTO = orderItemMapper.toDTO(orderItemEntity);
                orderItemDTOs.add(orderItemDTO);
            });
            orderDTO.setOrderItemEntities(orderItemDTOs);
            orderDTO.setAmount(orderEntity.getAmount());
            orderDTO.setCustomerId(orderEntity.getCustomerEntityId());
        });
        return orderDTO;
    }

    @Override
    public List<OrderDTO> getAllOrdersForCustomer(Long customerId) {

        List<OrderDTO> orderDTOs = new ArrayList<>();

        orderRepository.findAllByCustomerEntityId(customerId).forEach(orderEntity -> {

            List<OrderItemDTO> orderItemDTOs = new ArrayList<>();

            orderItemRepository.findByOrderEntityId(orderEntity.getId()).forEach(orderItemEntity -> {
                OrderItemDTO orderItemDTO = orderItemMapper.toDTO(orderItemEntity);
                orderItemDTOs.add(orderItemDTO);
            });
            OrderDTO orderDTO = orderMapper.toDTO(orderEntity);
            orderDTO.setOrderItemEntities(orderItemDTOs);
            orderDTOs.add(orderDTO);
        });
        return orderDTOs;
    }
}
