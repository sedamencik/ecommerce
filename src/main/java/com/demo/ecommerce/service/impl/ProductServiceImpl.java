package com.demo.ecommerce.service.impl;

import com.demo.ecommerce.dto.ProductRequestDTO;
import com.demo.ecommerce.dto.ProductResponseDTO;
import com.demo.ecommerce.entity.ProductEntity;
import com.demo.ecommerce.mapper.ProductMapper;
import com.demo.ecommerce.repository.ProductRepository;
import com.demo.ecommerce.service.ProductService;
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

        ProductEntity productEntity = productMapper.toProduct(productRequest);
        productEntity = productRepository.save(productEntity);
        return productMapper.toProductResponseDTO(productEntity);

    }

    @Override
    public ProductResponseDTO updateProduct(Long productId, ProductRequestDTO productRequest) {

        ProductEntity productEntity = productRepository.getReferenceById(productId);
        if (productEntity != null) {
            productEntity.setName(productRequest.getName());
            productEntity.setPrice(productRequest.getPrice());
            productEntity.setStock(productRequest.getStock());

            productEntity = productRepository.save(productEntity);
            return productMapper.toProductResponseDTO(productEntity);
        }
        return null;
    }

    @Override
    public void deleteProduct(Long productId) {
        ProductEntity productEntity = productRepository.getReferenceById(productId);
        if (productEntity != null) {
            productRepository.delete(productEntity);
        }
    }

    @Override
    public ProductResponseDTO getProduct(Long productId) {

        ProductEntity productEntity = productRepository.getReferenceById(productId);
        if (productEntity != null) {
            return productMapper.toProductResponseDTO(productEntity);
        }
        return null;
    }

    @Override
    public List<ProductResponseDTO> getAllProducts() {
        List<ProductEntity> productEntities = productRepository.findAll();

        List<ProductResponseDTO> productResponseDTOs = new ArrayList<>();
        for (ProductEntity productEntity : productEntities) {
            productResponseDTOs.add(productMapper.toProductResponseDTO(productEntity));
        }
        return productResponseDTOs;

    }

    @Override
    public Integer increaseStock(Long productId, Integer quantity) {
        ProductEntity productEntity = productRepository.getReferenceById(productId);
        Integer quantityToIncrease = quantity;

        if (productEntity.getStock() < quantity) {
            quantityToIncrease = productEntity.getStock();
            productEntity.setStock(0);
            productRepository.save(productEntity);
            System.out.println("Warning: Sepetinizde yeterli ürün bulunmamaktadır. Yalnızca " + quantityToIncrease + " adet ile sipariş oluşturulmuştur.");
        } else {
            productEntity.setStock(productEntity.getStock() - quantityToIncrease);
            productRepository.save(productEntity);
        }

        return quantityToIncrease;
    }
}
