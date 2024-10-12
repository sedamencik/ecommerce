package com.demo.ecommerce.mapper;

import com.demo.ecommerce.dto.CartDTO;
import com.demo.ecommerce.entity.CartEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CartMapper {
    CartEntity toEntity(CartDTO dto);
    CartDTO toDTO(CartEntity entity);
}
