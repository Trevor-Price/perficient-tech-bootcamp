package com.perficient.techbootcamp.ecommerce.controller;

import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;

import com.perficient.techbootcamp.ecommerce.dto.request.PlaceNewOrderItemDto;
import com.perficient.techbootcamp.ecommerce.dto.request.ChangeOrderStatusDto;
import com.perficient.techbootcamp.ecommerce.dto.response.OrderDto;
import com.perficient.techbootcamp.ecommerce.dto.response.OrderItemDto;
import com.perficient.techbootcamp.ecommerce.entity.OrderStatus;
import com.perficient.techbootcamp.ecommerce.service.impl.OrderServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
	
	@Autowired
	private OrderServiceImpl service;
	
	/**
	 * Get all Orders
	 * @return
	 */
	@GetMapping
	public ResponseEntity<List<OrderDto>> getAllOrders(){
		return ResponseEntity.ok(service.getAllOrders());
	}
	
	/**
	 * Get an Order
	 * @param orderId
	 * @return
	 */
	@GetMapping("/{orderId}")
	public ResponseEntity<OrderDto> getOrder(@PathVariable Long orderId){
		try{
			OrderDto order = service.getOrder(orderId);
			return ResponseEntity.ok(order);
		} catch(NoSuchElementException e){
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Failed to get order. Order not found for id: %d.", orderId), e);
		}
	}

	/**
	 * Place a new Order
	 * @param orderItems
	 * @return
	 */
	@PostMapping
	public ResponseEntity<OrderDto> postOrder(@RequestBody List<PlaceNewOrderItemDto> orderItems) {
		OrderDto order = service.placeOrder(orderItems);
		URI uri = URI.create("/api/orders"+order.getId());
		return ResponseEntity.created(uri).body(order);
	}

	/**
	 * Cancel an Order
	 * @param orderId
	 */
	@DeleteMapping("/{orderId}")
	public void deleteOrder(@PathVariable Long orderId){
		try {
			service.updateOrderStatus(orderId, OrderStatus.CANCELLED.name());
			ResponseEntity.noContent();
		} catch (EmptyResultDataAccessException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Failed to delete order. Order not found for id: %d.", orderId), e);
		}
		
	}
	
	/**
	 * Get all OrderItems for an Order
	 * @param orderId
	 * @return
	 */
	@GetMapping("/items/{orderId}")
	public ResponseEntity<List<OrderItemDto>> getAllOrderItems(@PathVariable Long orderId){
		try {
			List<OrderItemDto> orderItems = service.getAllOrderItems(orderId);
			return new ResponseEntity<List<OrderItemDto>>(orderItems, HttpStatus.OK);
		} catch(NoSuchElementException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Failed to get order items. Order not found for id: %d.", orderId));
		}
	}

	/**
	 * Update an OrderStatus for an Order
	 * @param orderId
	 * @param orderStatus
	 */
	@PutMapping("/status/{orderId}")
	public void updateOrderStatus(@PathVariable Long orderId, @RequestBody ChangeOrderStatusDto orderStatus){
		try{
			service.updateOrderStatus(orderId, orderStatus.getStatus());
			ResponseEntity.noContent();
		} catch(EmptyResultDataAccessException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Failed to update order status. Order not found for id: %d.", orderId), e);
		}
	}
	
}
