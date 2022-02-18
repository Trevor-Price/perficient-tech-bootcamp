package com.perficient.techbootcamp.ecommerce.service;

import java.util.List;

import com.perficient.techbootcamp.ecommerce.dto.response.ProductDto;

public interface ProductService{

    public List<ProductDto> getAllProducts();
	
	public ProductDto getProductById(long id);

}