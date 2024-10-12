package com.demo.ecommerce.mapper;

import com.demo.ecommerce.dto.CartItemDTO;
import com.demo.ecommerce.entity.CartItemEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CartItemMapper {
    CartItemDTO toDTO(CartItemEntity entity);
    CartItemEntity toEntity(CartItemDTO dto);
}
