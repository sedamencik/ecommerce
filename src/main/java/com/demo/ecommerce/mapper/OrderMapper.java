package com.demo.ecommerce.mapper;

import com.demo.ecommerce.dto.OrderDTO;
import com.demo.ecommerce.entity.OrderEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderDTO toDTO(OrderEntity orderEntity);
    OrderEntity toEntity(OrderDTO orderDTO);
}
