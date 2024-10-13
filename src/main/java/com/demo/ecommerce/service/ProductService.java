package com.demo.ecommerce.service;

import com.demo.ecommerce.dto.ProductRequestDTO;
import com.demo.ecommerce.dto.ProductResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    ProductResponseDTO createProduct(ProductRequestDTO productRequest);
    ProductResponseDTO updateProduct(Long productId, ProductRequestDTO productRequest);
    void deleteProduct(Long productId);
    ProductResponseDTO getProduct(Long productId);
    List<ProductResponseDTO> getAllProducts();
    Integer increaseStock(Long productId, Integer quantity);
}
