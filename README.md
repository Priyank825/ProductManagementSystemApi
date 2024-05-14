Product Management System API
This document provides instructions for running the Product Management System API, a Spring Boot application that allows users to perform CRUD operations on products.

Features:

CRUD operations (Create, Read, Update, Delete) for products
Basic authentication using Spring Security (Note: Currently uses a weak NoOpPasswordEncoder for demonstration purposes. Use a stronger encoder in production!)
Bean Validation (JSR-380) for data validation
Swagger API documentation
Technology Stack:

Spring Boot
Spring Data JPA
Spring Security
Bean Validation (JSR-380)
OpenAPI 3 (Springdoc)
Prerequisites:

Java 17+
Maven
Installation:

Clone this repository.
Open a terminal in the project directory.
Run mvn clean install to build the application.
Running the application:

Run mvn spring-boot:run to start the application.
Configuration:

The application properties are defined in application.properties. You can modify database connection details, server port, etc.
Security configuration is in SecurityConfig.java. Update usernames and passwords for production use.
API Endpoints:

Base URL: http://localhost:8080/api/products

Method	URI	Description

GET	/	Get all products
GET	/{id}	Get a product by ID
POST	/	Create a new product
PUT	/{id}	Update a product
DELETE	/{id}	Delete a product

drive_spreadsheet
Export to Sheets
Authentication:

The API uses basic authentication. You can send credentials in the Authorization header:

Authorization: Basic username:password
Replace username and password with the credentials configured in application.yml.

Testing the API:

You can use tools like Postman to test the API endpoints.

API Documentation:

API documentation is available using Swagger at http://localhost:8080/swagger-ui/.

Running Tests:

Unit tests are included in the src/test/java directory. You can run them using Maven:

mvn test
Contributing:

Pull requests and bug reports are welcome!

License:

This project is licensed under the Apache License 2.0. See the LICENSE file for details"# ProductManagementSystemApi" 
