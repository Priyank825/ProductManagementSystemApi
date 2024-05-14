package com.product.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.product.model.Product;
import com.product.service.ProductService;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class ProductControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllProducts() {
        // Mocking the service method
        List<Product> productList = new ArrayList<>();
        productList.add(new Product(1L, "Product 1", "Description 1", 10.00, 5));
        productList.add(new Product(2L, "Product 2", "Description 2", 20.00, 10));
        when(productService.getAllProducts()).thenReturn(productList);

        // Calling the controller method
        ResponseEntity<List<Product>> responseEntity = productController.getAllProducts();

        // Verifying the response
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(2, responseEntity.getBody().size());
    }

    @Test
    public void testGetProductById() {
        // Mocking the service method
        Product product = new Product(1L, "Product 1", "Description 1", 10.00, 5);
        when(productService.getProductById(1L)).thenReturn(product);

        // Calling the controller method
        ResponseEntity<Product> responseEntity = productController.getProductById(1L);

        // Verifying the response
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(1L, responseEntity.getBody().getId());
    }

    @Test
    public void testCreateProduct() {
        // Mocking the service method
        Product product = new Product(1L, "Product 1", "Description 1", 10.00, 5);
        when(productService.createProduct(any())).thenReturn(product);

        // Calling the controller method
        ResponseEntity<Product> responseEntity = productController.createProduct(product);

        // Verifying the response
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(1L, responseEntity.getBody().getId());
    }

    @Test
    public void testUpdateProduct() {
        // Mocking the service method
        Product updatedProduct = new Product(1L, "Updated Product", "Updated Description", 15.00, 10);
        when(productService.updateProduct(1L, updatedProduct)).thenReturn(updatedProduct);

        // Calling the controller method
        ResponseEntity<Product> responseEntity = productController.updateProduct(1L, updatedProduct);

        // Verifying the response
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Updated Product", responseEntity.getBody().getName());
    }

    @Test
    public void testDeleteProduct() {
        // Calling the controller method
        ResponseEntity<Void> responseEntity = productController.deleteProduct(1L);

        // Verifying the response
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        verify(productService, times(1)).deleteProduct(1L);
    }
}

