package com.perficient.techbootcamp.eCommerce.controller.responsebody;

import java.util.List;

import com.perficient.techbootcamp.eCommerce.entity.Orders;
import com.perficient.techbootcamp.eCommerce.entity.Product;

public class OrderItemsResponse {
	
	private Orders order;
	private List<Product> orderItems;
	
	public OrderItemsResponse() {
		super();
	}
	
	public OrderItemsResponse(Orders order, List<Product> orderItems) {
		super();
		this.order = order;
		this.orderItems = orderItems;
	}

	public Orders getOrder() {
		return order;
	}

	public void setOrder(Orders order) {
		this.order = order;
	}

	public List<Product> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<Product> orderItems) {
		this.orderItems = orderItems;
	}
	
}
