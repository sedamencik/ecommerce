package com.demo.ecommerce.mapper;

import com.demo.ecommerce.dto.CartDTO;
import com.demo.ecommerce.entity.Cart;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CartMapper {
    Cart toEntity(CartDTO dto);
    CartDTO toDTO(Cart entity);
}
