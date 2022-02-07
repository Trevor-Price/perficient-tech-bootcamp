package com.perficient.techbootcamp.eCommerce.controller.responsebody;

import com.perficient.techbootcamp.eCommerce.entity.Product;

public class ProductQuantity {

	private Product product;
	private Integer quantity;
	
	public ProductQuantity() {
		super();
	}
	
	public ProductQuantity(Product product, Integer quantity) {
		super();
		this.product = product;
		this.quantity = quantity;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	
	
	
	
}
