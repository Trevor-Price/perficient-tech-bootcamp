package com.perficient.techbootcamp.ecommerce.controller;

import java.net.URI;
import java.util.List;

import com.perficient.techbootcamp.ecommerce.dto.request.PlaceNewOrderItemDto;
import com.perficient.techbootcamp.ecommerce.dto.request.ChangeOrderStatusDto;
import com.perficient.techbootcamp.ecommerce.dto.response.OrderDto;
import com.perficient.techbootcamp.ecommerce.dto.response.OrderItemDto;
import com.perficient.techbootcamp.ecommerce.service.impl.OrderServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
	
	@Autowired
	private OrderServiceImpl service;
	
	@GetMapping
	public ResponseEntity<List<OrderDto>> getAllOrders(){
		return ResponseEntity.ok(
			service.getAllOrders()
		);
	}
	
	@GetMapping("/{orderId}")
	public ResponseEntity<OrderDto> getOrder(@PathVariable Long orderId){
		return ResponseEntity.ok(
			service.getOrder(orderId)
		);
	}

	@PostMapping
	public ResponseEntity<OrderDto> placeOrder(@RequestBody List<PlaceNewOrderItemDto> orderItems) {
		OrderDto order = service.placeOrder(orderItems);
		return ResponseEntity.created(
			URI.create("/api/orders"+order.getId()))
			.body(order);
	}

	@GetMapping("/items/{orderId}")
	public ResponseEntity<List<OrderItemDto>> getAllOrderItems(@PathVariable Long orderId){
		return ResponseEntity.ok(
			service.getAllOrderItems(orderId)
		);
	}

	@PutMapping("/status/{orderId}")
	public ResponseEntity<OrderDto> updateOrderStatus(@PathVariable Long orderId, @RequestBody ChangeOrderStatusDto orderStatus){
		return ResponseEntity.ok(
			service.updateOrderStatus(orderId, orderStatus.getStatus())
		);
	}
	
}
