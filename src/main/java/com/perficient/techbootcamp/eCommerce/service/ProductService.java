package com.perficient.techbootcamp.eCommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.perficient.techbootcamp.eCommerce.entity.Product;
import com.perficient.techbootcamp.eCommerce.repository.ProductRepository;

@Service
public class ProductService{
	
	@Autowired
	private ProductRepository repository;
	
	public List<Product> getAllProducts() {
		List<Product> products = (List<Product>)repository.findAll();
		return products; 
	}
	
	public Optional<Product> getProductById(long id) {
		return repository.findById(id);
	}
	
}
