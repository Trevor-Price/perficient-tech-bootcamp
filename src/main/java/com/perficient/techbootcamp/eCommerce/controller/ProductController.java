package com.perficient.techbootcamp.eCommerce.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.perficient.techbootcamp.eCommerce.entity.Product;
import com.perficient.techbootcamp.eCommerce.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	private ProductService service;
	
	@GetMapping
	List<Product> all(){
		return service.getAllProducts();
	}
	
	@GetMapping("/{id}")
	Optional<Product> one(@PathVariable Long id) {
		return service.getProductById(id);
	}
	
}
