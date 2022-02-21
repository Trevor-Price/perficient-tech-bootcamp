package com.perficient.techbootcamp.ecommerce.controller;

import java.util.List;

import com.perficient.techbootcamp.ecommerce.dto.response.ProductDto;
import com.perficient.techbootcamp.ecommerce.service.impl.ProductServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class ProductController {
	
	@Autowired
	private ProductServiceImpl service;
	
	@GetMapping
	public ResponseEntity<List<ProductDto>> getAllProducts(){
		return ResponseEntity.ok(
			service.getAllProducts()
		);
	}
	
	@GetMapping("/{productId}")
	public ResponseEntity<ProductDto> getProduct(@PathVariable Long productId) {
		return ResponseEntity.ok(
			service.getProductById(productId)
		);
	}
	
}
