package com.perficient.techbootcamp.ecommerce.service;

import java.util.List;

import com.perficient.techbootcamp.ecommerce.dto.request.PlaceNewOrderItemDto;
import com.perficient.techbootcamp.ecommerce.dto.response.OrderDto;
import com.perficient.techbootcamp.ecommerce.dto.response.OrderItemDto;

public interface OrderService{

    public List<OrderDto> getAllOrders();

	public OrderDto getOrder(Long orderId);
	
	public List<OrderItemDto> getAllOrderItems(Long orderId);
	
	public OrderDto placeOrder(List<PlaceNewOrderItemDto> orderItems);

	public OrderDto updateOrderStatus(Long orderId, String orderStatus);

}