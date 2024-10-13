package com.demo.ecommerce.controller;

import com.demo.ecommerce.dto.CustomerRequestDTO;
import com.demo.ecommerce.dto.CustomerResponseDTO;
import com.demo.ecommerce.service.CustomerService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping("/api/customers")
@Tag(name = "Müşteri Yönetimi", description = "Müşteri ile ilgili CRUD işlemleri")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @Operation(summary = "Yeni Müşteri Ekle", description = "Sisteme yeni bir müşteri ekler.")
    @PostMapping
    public ResponseEntity<CustomerResponseDTO> addCustomer( @ModelAttribute CustomerRequestDTO customerRequest) {
        System.out.println("Controller " + customerRequest);
        if (customerRequest.getEmail() == null || customerRequest.getEmail().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        try {
            CustomerResponseDTO response = customerService.addCustomer(customerRequest);
            return new ResponseEntity<>(response, HttpStatus.CREATED);

        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @Operation(summary = "algoritma")
    @PostMapping("/algoritma")
    public ResponseEntity<String> algoritma() {

        int[] primaryArray = new int[100];
        Random random = new Random();

        for (int i = 0; i < primaryArray.length; i++) {
            primaryArray[i] = random.nextInt(1000);
        }

        int[] secondaryArray = new int[99];
        int randomIndex = random.nextInt(100);

        for (int i = 0; i < secondaryArray.length; i++) {
            if(i == randomIndex) {
                continue;
            }
            secondaryArray[i] = primaryArray[randomIndex];
        }














        return new ResponseEntity<>("algoritma", HttpStatus.OK);


    }

    int findMissingNum(int[] primaryAr, int[] secondaryAr) {

        for(int i = 0; i < primaryAr.length; i++) {
            boolean isFound = false;
            for(int j = 0; j < secondaryAr.length; j++) {
                if(primaryAr[i] != secondaryAr[j]) {
                    isFound = true;
                    break;
                }
            }
            if(isFound) {
                return primaryAr[i];
            }
        }
        return -1;
    }
}

