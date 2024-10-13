package com.demo.ecommerce.mapper;

import com.demo.ecommerce.dto.OrderItemDTO;
import com.demo.ecommerce.entity.CartItemEntity;
import com.demo.ecommerce.entity.OrderItemEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {
    OrderItemDTO toDTO(OrderItemEntity orderItemEntity);
    OrderItemEntity toEntity(OrderItemDTO orderItemDTO);
}
