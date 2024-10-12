package com.demo.ecommerce.controller;

import com.demo.ecommerce.dto.CartDTO;
import com.demo.ecommerce.service.CartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/carts")
@Tag(name = "Sepet Yönetimi", description = "Sepet ile ilgili CRUD işlemleri")
public class CartController {

    @Autowired
    private CartService cartService;

    @Operation(summary = "Sepeti Getir", description = "Müşteriye ait sepet bilgilerini getirir.")
    @GetMapping("/{customerId}")
    public ResponseEntity<CartDTO> getCart(@PathVariable Long customerId) {
        CartDTO cart = cartService.getCart(customerId);
        if (cart != null) {
            return new ResponseEntity<>(cart, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Sepeti Boşalt", description = "Müşteriye ait sepetteki ürünleri siler.")
    @PutMapping("/{customerId}")
    public ResponseEntity<Void> emptyCart(@PathVariable Long customerId) {
        cartService.emptyCart(customerId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Ürün Ekle", description = "Müşteriye ait sepete ürün ekler.")
    @PostMapping("/{customerId}/{productId}/{quantity}")
    public ResponseEntity<Void> addProductToCart(@PathVariable Long customerId, @PathVariable Long productId, @PathVariable Integer quantity) {
        cartService.addProductToCart(customerId, productId, quantity);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @Operation(summary = "Ürün Eksilt", description = "Müşteriye ait sepetteki ürünü 1 adet siler.")
    @PutMapping("/{customerId}/{productId}")
    public ResponseEntity<CartDTO> removeProductFromCart(@PathVariable Long customerId, @PathVariable Long productId) {
        CartDTO cart = cartService.removeProductFromCart(customerId, productId);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }
}
