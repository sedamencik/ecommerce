package com.demo.ecommerce.mapper;

import com.demo.ecommerce.dto.ProductRequestDTO;
import com.demo.ecommerce.dto.ProductResponseDTO;
import com.demo.ecommerce.entity.ProductEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductResponseDTO toProductResponseDTO(ProductEntity productEntity);
    ProductEntity toProduct(ProductRequestDTO productRequest) ;
}
