package com.perficient.techbootcamp.ecommerce.service.impl;

import java.util.List;
import java.util.Optional;

import com.perficient.techbootcamp.ecommerce.dto.mapper.ProductDtoMapper;
import com.perficient.techbootcamp.ecommerce.dto.response.ProductDto;
import com.perficient.techbootcamp.ecommerce.entity.Product;
import com.perficient.techbootcamp.ecommerce.repository.ProductRepository;
import com.perficient.techbootcamp.ecommerce.service.ProductService;
import com.perficient.techbootcamp.ecommerce.service.exception.ProductNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductRepository repository;
	
	public List<ProductDto> getAllProducts() {
		List<Product> products = (List<Product>) repository.findAll();
		return products
			.stream()
			.map(product -> ProductDtoMapper.toProductDto(product))
			.toList();
	}

	public ProductDto getProductById(long id) {
		Optional<Product> product = repository.findById(id);
		if(product.isPresent()){
			return ProductDtoMapper.toProductDto(product.get());
		}
		throw new ProductNotFoundException();
	}
	
}
