package com.perficient.techbootcamp.eCommerce.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.perficient.techbootcamp.eCommerce.controller.requestbody.PostOrderItem;
import com.perficient.techbootcamp.eCommerce.entity.OrderItem;
import com.perficient.techbootcamp.eCommerce.entity.Orders;
import com.perficient.techbootcamp.eCommerce.service.OrderService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
	
	@Autowired
	private OrderService service;
	
	@GetMapping
	public ResponseEntity<List<Orders>> getAllOrders(){
		return ResponseEntity.ok(service.getAllOrders());
	}
	
	@PostMapping
	public ResponseEntity<Orders> postOrder(@RequestBody List<PostOrderItem> orderItems) {
		Orders order = service.placeOrder(orderItems);
		return new ResponseEntity<Orders>(order, HttpStatus.CREATED);
	}
	
	@GetMapping("/{orderId}")
	public List<OrderItem> getAllOrderItems(@PathVariable Long orderId){
		try {
			return service.getAllOrderItems(orderId);
		} catch(NoSuchElementException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Failed to get order items. Order not found for id: %d.", orderId));
		}
	}
	
	@DeleteMapping("/{orderId}")
	public void deleteOrder(@PathVariable Long orderId){
		try {
			service.cancelOrder(orderId);
			ResponseEntity.noContent();
		} catch (EmptyResultDataAccessException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Failed to delete order. Order not found for id: %d.", orderId), e);
		}
		
	}
	
}
