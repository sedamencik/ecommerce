# E-commerce Backend with Java Spring Boot

E-commerce backend application developed using Java Spring Boot. This project handles functionalities like managing products, orders, and carts for an e-commerce platform.

## Table of Contents

- [About the Project](#about-the-project)
- [Technologies Used](#technologies-used)
- [Features](#features)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
- [Usage](#usage)
  - [API Endpoints](#api-endpoints)
  - [H2Database](#h2-database)


## About the Project

This project is an e-commerce backend that supports operations such as product listing, cart management, and order creation. It is built using Spring Boot and follows best practices in software design, such as the use of Service and Repository layers.

## Technologies Used

- **Java 17**
- **Spring Boot 3**
- **Hibernate/JPA**
- **H2 Database**
- **Spring Data JPA**
- **Lombok** (to reduce boilerplate code)
- **Maven** (build automation)
- **Swagger**
  
## Features

- Product management (add, update, delete, list products)
- Cart operations (add items to cart, remove items, update quantity)
- Order management (place orders, view customer orders)
- Customer management

## Getting Started

To get a local copy of this project up and running, follow these steps.

### Prerequisites

- Java Development Kit (JDK) 17 or later
- Maven
- Git
- An IDE like IntelliJ IDEA or Eclipse

### Installation

1. **Clone the repository**

   ```bash
   git clone https://github.com/your-username/ecommerce-backend.git
   cd ecommerce-backend

2. **Configure the database**

Update the application.properties file with your H2 database details:

  ```bash
  spring.datasource.url=jdbc:h2:mem:ecommerce-db
  spring.datasource.driverClassName=org.h2.Driver
  spring.datasource.username=sa
  spring.datasource.password=
  spring.sql.init.platform=h2
  ```


3. **Build and Run the project**

  ```bash
   mvn clean install
   mvn spring-boot:run
  ```

## Usage

1. **API Endpoints**
   
   http://localhost:8009/swagger-ui/index.html#/
   
   ![Image 14 10 2024 at 01 15](https://github.com/user-attachments/assets/7ade157f-a415-42ab-ab42-b1e2c854f1ef)


2. **H2Database**

   http://localhost:8009/h2-console/
   
   ![Image 14 10 2024 at 01 12](https://github.com/user-attachments/assets/33f4e0ce-bd8a-406a-9174-900efe027ea6)




