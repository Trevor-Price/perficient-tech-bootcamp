package com.perficient.techbootcamp.ecommerce.service;

import java.util.List;

import com.perficient.techbootcamp.ecommerce.entity.Product;
import com.perficient.techbootcamp.ecommerce.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImp implements ProductService{
	
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
