package com.demo.ecommerce.controller;

import com.demo.ecommerce.dto.ProductRequestDTO;
import com.demo.ecommerce.dto.ProductResponseDTO;
import com.demo.ecommerce.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@Tag(name = "Ürün Yönetimi", description = "Ürün ile ilgili CRUD işlemleri")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Operation(summary = "Ürün Listele", description = "Sistemdeki tüm ürünleri listeler.")
    @GetMapping("/all")
    public List<ProductResponseDTO> getProducts() {
        return productService.getAllProducts();
    }

    @Operation(summary = "Ürün Detay", description = "Ürün detaylarını getirir.")
    @GetMapping("/{id}")
    public ProductResponseDTO getProduct(@PathVariable Long id) {
        return productService.getProduct(id);
    }

    @Operation(summary="Ürün Ekle", description = "Sisteme yeni bir ürün ekler.")
    @PostMapping
    public ProductResponseDTO addProduct(@RequestBody ProductRequestDTO productRequest) {
        return productService.createProduct(productRequest);
    }

    @Operation(summary="Ürün Güncelle", description = "Sistemdeki bir ürünü günceller.")
    @PutMapping("/{id}")
    public ProductResponseDTO updateProduct(@PathVariable Long id, @RequestBody ProductRequestDTO productRequest) {
        return productService.updateProduct(id, productRequest);
    }

    @Operation(summary="Ürün Sil", description = "Sistemdeki bir ürünü siler.")
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }

}
