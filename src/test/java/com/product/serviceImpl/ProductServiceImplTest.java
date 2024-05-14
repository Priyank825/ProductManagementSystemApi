package com.product.serviceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.product.exception.NotFoundException;
import com.product.model.Product;
import com.product.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllProducts() {
        // Mocking the repository method
        List<Product> productList = new ArrayList<>();
        productList.add(new Product(1L, "Product 1", "Description 1", 10.00, 5));
        productList.add(new Product(2L, "Product 2", "Description 2", 20.00, 10));
        when(productRepository.findAll()).thenReturn(productList);

        // Calling the service method
        List<Product> result = productService.getAllProducts();

        // Verifying the response
        assertEquals(2, result.size());
    }

    @Test
    public void testGetProductById() {
        // Mocking the repository method
        Product product = new Product(1L, "Product 1", "Description 1", 10.00, 5);
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        // Calling the service method
        Product result = productService.getProductById(1L);

        // Verifying the response
        assertEquals(1L, result.getId());
    }

    @Test
    public void testCreateProduct() {
        // Mocking the repository method
        Product product = new Product(1L, "Product 1", "Description 1", 10.00, 5);
        when(productRepository.save(any())).thenReturn(product);

        // Calling the service method
        Product result = productService.createProduct(product);

        // Verifying the response
        assertEquals(1L, result.getId());
    }

    @Test
    public void testUpdateProduct() {
        // Mocking the repository method
        Product product = new Product(1L, "Product 1", "Description 1", 10.00, 5);
        when(productRepository.existsById(1L)).thenReturn(true);
        when(productRepository.save(any())).thenReturn(product);

        // Calling the service method
        Product result = productService.updateProduct(1L, product);

        // Verifying the response
        assertEquals("Product 1", result.getName());
    }

    @Test
    public void testDeleteProduct() {
        // Mocking the repository method
        when(productRepository.existsById(1L)).thenReturn(true);

        // Calling the service method
        productService.deleteProduct(1L);

        // Verifying that deleteById is called
        verify(productRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testGetProductById_WhenProductNotFound() {
        // Mocking the repository method
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        // Verifying that NotFoundException is thrown
        assertThrows(NotFoundException.class, () -> productService.getProductById(1L));
    }

    @Test
    public void testUpdateProduct_WhenProductNotFound() {
        // Mocking the repository method
        when(productRepository.existsById(1L)).thenReturn(false);

        // Verifying that NotFoundException is thrown
        assertThrows(NotFoundException.class, () -> productService.updateProduct(1L, new Product()));
    }

    @Test
    public void testDeleteProduct_WhenProductNotFound() {
        // Mocking the repository method
        when(productRepository.existsById(1L)).thenReturn(false);

        // Verifying that NotFoundException is thrown
        assertThrows(NotFoundException.class, () -> productService.deleteProduct(1L));
    }
}
