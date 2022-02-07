package com.perficient.techbootcamp.eCommerce.product;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/products")
public class ProductController {
	
	@Autowired
	private ProductService service;
	
	@GetMapping
	public List<Product> getAllProducts(){
		return service.getAllProducts();
	}
	
	@GetMapping("/{productId}")
	public ResponseEntity<Product> getProduct(@PathVariable Long productId) {
		try{
			return ResponseEntity.ok(service.getProductById(productId));
		} catch (NoSuchElementException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Product not found for id: %d.", productId), e);
		}
	}
	
}
