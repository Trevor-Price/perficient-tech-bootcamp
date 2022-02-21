package com.perficient.techbootcamp.ecommerce.service;

import java.util.List;

import com.perficient.techbootcamp.ecommerce.dto.request.PlaceNewOrderItemDto;
import com.perficient.techbootcamp.ecommerce.dto.response.OrderDto;
import com.perficient.techbootcamp.ecommerce.dto.response.OrderItemDto;
import com.perficient.techbootcamp.ecommerce.service.exception.InvalidOrderStatusException;
import com.perficient.techbootcamp.ecommerce.service.exception.OrderNotFoundException;

public interface OrderService{

    public List<OrderDto> getAllOrders();

	public OrderDto getOrder(Long orderId) throws OrderNotFoundException;
	
	public List<OrderItemDto> getAllOrderItems(Long orderId) throws OrderNotFoundException;
	
	public OrderDto placeOrder(List<PlaceNewOrderItemDto> orderItems);

	public OrderDto updateOrderStatus(Long orderId, String orderStatus) throws OrderNotFoundException, InvalidOrderStatusException;

}