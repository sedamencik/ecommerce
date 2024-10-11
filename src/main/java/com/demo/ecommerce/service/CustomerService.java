package com.demo.ecommerce.service;

import com.demo.ecommerce.dto.CustomerRequestDTO;
import com.demo.ecommerce.dto.CustomerResponseDTO;
import org.springframework.stereotype.Service;

@Service
public interface CustomerService {
    CustomerResponseDTO addCustomer(CustomerRequestDTO customerRequest);
}
