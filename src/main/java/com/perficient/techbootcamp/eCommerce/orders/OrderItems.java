package com.perficient.techbootcamp.eCommerce.orders;

import java.util.List;

import com.perficient.techbootcamp.eCommerce.entity.Orders;

public class OrderItems {
	
	private Orders order;
	private List<ProductQuantity> productQuantities;
	
	public OrderItems() {
		super();
	}
	
	public OrderItems(Orders order, List<ProductQuantity> productQuantities) {
		super();
		this.order = order;
		this.productQuantities = productQuantities;
	}

	public Orders getOrder() {
		return order;
	}

	public void setOrder(Orders order) {
		this.order = order;
	}

	public List<ProductQuantity> getProductQuantities() {
		return productQuantities;
	}

	public void setProductQuantities(List<ProductQuantity> productQuantities) {
		this.productQuantities = productQuantities;
	}
	
}
