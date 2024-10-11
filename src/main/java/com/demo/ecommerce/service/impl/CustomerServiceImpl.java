package com.demo.ecommerce.service.impl;

import com.demo.ecommerce.dto.CustomerRequestDTO;
import com.demo.ecommerce.dto.CustomerResponseDTO;
import com.demo.ecommerce.entity.Cart;
import com.demo.ecommerce.entity.Customer;
import com.demo.ecommerce.mapper.CustomerMapper;
import com.demo.ecommerce.repository.CustomerRepository;
import com.demo.ecommerce.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    //@Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    public CustomerResponseDTO addCustomer(CustomerRequestDTO customerRequest) {
        Customer customer = customerMapper.toEntity(customerRequest);

        // Yeni bir Cart oluştur ve Customer ile ilişkilendir
        Cart cart = new Cart();
        cart.setCustomer(customer);
        customer.setCart(cart);

        customer = customerRepository.save(customer);

        return customerMapper.toDTO(customer);
    }
}
