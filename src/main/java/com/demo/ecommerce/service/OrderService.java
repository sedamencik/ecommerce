package com.demo.ecommerce.service;
import com.demo.ecommerce.dto.OrderDTO;

import java.util.List;

public interface OrderService {
    void placeOrder(Long customerId);
    OrderDTO getOrderForCode(Long orderId);
    List<OrderDTO> getAllOrdersForCustomer(Long customerId);
}
