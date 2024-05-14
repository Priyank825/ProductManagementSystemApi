package com.product.serviceImpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.product.exception.NotFoundException;
import com.product.model.Product;
import com.product.repository.ProductRepository;
import com.product.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    
    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

    @Override
    public List<Product> getAllProducts() {
    	logger.info("Fetching all products");
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Long id) {
    	logger.info("Fetching product with id {}", id);
        return productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product not found"));
    }
    
    @Override
    public Product createProduct(Product product) {
    	logger.info("Creating product");
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long id, Product product) {
    	logger.info("Updating product with id {}", id);
        if (!productRepository.existsById(id)) {
            throw new NotFoundException("Product not found");
        }
        product.setId(id);
        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long id) {
    	logger.info("Deleting product with id {}", id);
        if (!productRepository.existsById(id)) {
            throw new NotFoundException("Product not found");
        }
        productRepository.deleteById(id);
    }
}