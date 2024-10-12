package com.demo.ecommerce.service.impl;

import com.demo.ecommerce.dto.CustomerRequestDTO;
import com.demo.ecommerce.dto.CustomerResponseDTO;
import com.demo.ecommerce.entity.CartEntity;
import com.demo.ecommerce.entity.CustomerEntity;
import com.demo.ecommerce.mapper.CustomerMapper;
import com.demo.ecommerce.repository.CartRepository;
import com.demo.ecommerce.repository.CustomerRepository;
import com.demo.ecommerce.service.CustomerService;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final CartRepository cartRepository;

    //@Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper, CartRepository cartRepository) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
        this.cartRepository = cartRepository;
    }

    @Override
    public CustomerResponseDTO addCustomer(CustomerRequestDTO customerRequest) {
        CustomerEntity customerEntity = customerMapper.toEntity(customerRequest);

        customerRepository.save(customerEntity);

        CartEntity cartEntity = new CartEntity(customerEntity.getId());
        cartRepository.save(cartEntity);

        return customerMapper.toDTO(customerEntity);
    }
}
