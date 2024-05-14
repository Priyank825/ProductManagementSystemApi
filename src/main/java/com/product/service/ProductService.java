package com.product.service;

import java.util.List;

import com.product.model.Product;

public interface ProductService {
	
	public List<Product> getAllProducts();
	
	public Product getProductById(Long id);
	
	public Product createProduct(Product product);

	public Product updateProduct(Long id, Product product);
	
	public void deleteProduct(Long id);
}
