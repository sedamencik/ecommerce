package com.demo.ecommerce.mapper;

import com.demo.ecommerce.dto.CartItemDTO;
import com.demo.ecommerce.entity.CartItem;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CartItemMapper {
    CartItemDTO toDTO(CartItem entity);
    CartItem toEntity(CartItemDTO dto);
}
