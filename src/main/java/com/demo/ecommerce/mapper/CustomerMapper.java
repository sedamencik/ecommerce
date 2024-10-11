package com.demo.ecommerce.mapper;

import com.demo.ecommerce.dto.CustomerRequestDTO;
import com.demo.ecommerce.dto.CustomerResponseDTO;
import com.demo.ecommerce.entity.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    Customer toEntity(CustomerRequestDTO dto);
    CustomerResponseDTO toDTO(Customer entity);
}
