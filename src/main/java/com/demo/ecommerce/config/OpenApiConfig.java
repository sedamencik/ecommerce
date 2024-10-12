package com.demo.ecommerce.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("E-Ticaret API")
                        .version("v1.0")
                        .description("Spring Boot E-Ticaret Projesi API Dokümantasyonu")
                        .contact(new Contact().name("Seda Mencik").email("seda.mencik@gmail.com"))
                        .license(new License().name("Linkedin Account").url("https://www.linkedin.com/in/seda-mencik/")))
                .components(new Components()
                        .addSecuritySchemes("BearerScheme",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")))
                .addSecurityItem(new SecurityRequirement().addList("BearerScheme"));
    }
}
