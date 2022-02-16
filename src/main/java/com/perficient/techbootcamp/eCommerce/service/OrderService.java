package com.perficient.techbootcamp.ecommerce.service;

import java.util.List;

import com.perficient.techbootcamp.ecommerce.entity.OrderStatus;
import com.perficient.techbootcamp.ecommerce.entity.Orders;
import com.perficient.techbootcamp.ecommerce.request.OrderItemPostRequestBody;
import com.perficient.techbootcamp.ecommerce.response.OrderItemsResponseBody;

public interface OrderService{

    public List<Orders> getAllOrders();
	
	public OrderItemsResponseBody getAllOrderItems(Long orderId);
	
	public Orders placeOrder(List<OrderItemPostRequestBody> items);

	public void updateOrderStatus(Long orderId, OrderStatus orderStatus);
		
	public void cancelOrder(Long orderId) throws IllegalArgumentException;
}