package com.demo.ecommerce.controller;

import com.demo.ecommerce.dto.OrderDTO;
import com.demo.ecommerce.service.CartService;
import com.demo.ecommerce.service.OrderService;
import com.demo.ecommerce.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@Tag(name = "Sipariş Yönetimi", description = "Sipariş ile ilgili CRUD işlemleri")
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private CartService cartService;
    @Autowired
    private ProductService productService;

    @Operation(summary = "Sipariş Oluştur", description = "Müşteriye ait sepetin siparişini oluşturur ve sepeti boşaltır.")
    @PostMapping("/{customerId}")
    public ResponseEntity<Void> placeOrder( Long customerId) {
        orderService.placeOrder(customerId);
        cartService.emptyCart(customerId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Sipariş Getir", description = "Sipariş detaylarını getirir.")
    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDTO> getOrder( Long orderId) {
        OrderDTO order = orderService.getOrderForCode(orderId);
        if (order == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @Operation(summary = "Siparişleri Getir", description = "Müşteriye ait tüm siparişleri getirir.")
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<OrderDTO>> getOrders(@PathVariable Long customerId) {
        List<OrderDTO> orders = orderService.getAllOrdersForCustomer(customerId);
        if (orders == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }
}
