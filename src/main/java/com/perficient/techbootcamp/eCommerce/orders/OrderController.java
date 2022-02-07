package com.perficient.techbootcamp.eCommerce.orders;

import java.net.URI;
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
		URI uri = URI.create("/api/orders"+order.getOrderId());
		return ResponseEntity.created(uri).body(order);
	}
	
	@GetMapping("/{orderId}")
	public ResponseEntity<OrderItems> getAllOrderItems(@PathVariable Long orderId){
		try {
			OrderItems orderItems = service.getAllOrderItems(orderId);
			return new ResponseEntity<OrderItems>(orderItems, HttpStatus.OK);
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
