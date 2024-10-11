package com.demo.ecommerce.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class CustomerRequestDTO {
    @Schema(description = "Müşterinin adı", example = "Ali Veli", required = true)
    @NotBlank(message = "Name is mandatory")
    private String name;

    @Schema(description = "Müşterinin e-posta adresi", example = "ali.veli@example.com", required = true)
    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is mandatory")
    private String email;


    CustomerRequestDTO(String name, String email){
        this.name = name;
        this.email = email;
    }
    // Getters and Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

