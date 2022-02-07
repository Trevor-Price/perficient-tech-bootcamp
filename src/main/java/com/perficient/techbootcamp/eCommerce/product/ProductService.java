package com.perficient.techbootcamp.eCommerce.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService{
	
	@Autowired
	private ProductRepository repository;
	
	public List<Product> getAllProducts() {
		List<Product> products = (List<Product>)repository.findAll();
		return products; 
	}
	
	public Product getProductById(long id) {
		return repository.findById(id).orElseThrow();
	}
	
}
