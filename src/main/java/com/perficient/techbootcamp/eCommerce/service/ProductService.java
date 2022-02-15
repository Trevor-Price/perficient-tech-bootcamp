package com.perficient.techbootcamp.ecommerce.service;

import java.util.List;

import com.perficient.techbootcamp.ecommerce.entity.Product;

public interface ProductService{

    public List<Product> getAllProducts();
	
	public Product getProductById(long id);

}