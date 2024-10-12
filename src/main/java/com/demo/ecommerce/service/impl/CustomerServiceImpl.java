package com.demo.ecommerce.service.impl;

import com.demo.ecommerce.dto.CustomerRequestDTO;
import com.demo.ecommerce.dto.CustomerResponseDTO;
import com.demo.ecommerce.entity.Cart;
import com.demo.ecommerce.entity.Customer;
import com.demo.ecommerce.mapper.CustomerMapper;
import com.demo.ecommerce.repository.CartRepository;
import com.demo.ecommerce.repository.CustomerRepository;
import com.demo.ecommerce.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
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
        Customer customer = customerMapper.toEntity(customerRequest);

        customerRepository.save(customer);

        Cart cart = new Cart(customer.getId());
        cartRepository.save(cart);

        return customerMapper.toDTO(customer);
    }
}
