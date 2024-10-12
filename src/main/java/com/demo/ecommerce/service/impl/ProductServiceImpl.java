package com.demo.ecommerce.service.impl;

import com.demo.ecommerce.dto.ProductRequestDTO;
import com.demo.ecommerce.dto.ProductResponseDTO;
import com.demo.ecommerce.entity.Product;
import com.demo.ecommerce.mapper.ProductMapper;
import com.demo.ecommerce.repository.ProductRepository;
import com.demo.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductMapper productMapper;
    private final ProductRepository productRepository;
    public ProductServiceImpl(ProductMapper productMapper, ProductRepository productRepository) {
        this.productMapper = productMapper;
        this.productRepository = productRepository;
    }

    @Override
    public ProductResponseDTO createProduct(ProductRequestDTO productRequest) {

        Product product = productMapper.toProduct(productRequest);
        product = productRepository.save(product);
        return productMapper.toProductResponseDTO(product);

    }

    @Override
    public ProductResponseDTO updateProduct(Long productId, ProductRequestDTO productRequest) {

        Product product = productRepository.getReferenceById(productId);
        if (product != null) {
            product.setName(productRequest.getName());
            product.setPrice(productRequest.getPrice());
            product.setStock(productRequest.getStock());

            product = productRepository.save(product);
            return productMapper.toProductResponseDTO(product);
        }
        return null;
    }

    @Override
    public void deleteProduct(Long productId) {
        Product product = productRepository.getReferenceById(productId);
        if (product != null) {
            productRepository.delete(product);
        }
    }

    @Override
    public ProductResponseDTO getProduct(Long productId) {

        Product product = productRepository.getReferenceById(productId);
        if (product != null) {
            return productMapper.toProductResponseDTO(product);
        }
        return null;
    }

    @Override
    public List<ProductResponseDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();

        List<ProductResponseDTO> productResponseDTOs = new ArrayList<>();
        for (Product product : products) {
            productResponseDTOs.add(productMapper.toProductResponseDTO(product));
        }
        return productResponseDTOs;

    }
}
