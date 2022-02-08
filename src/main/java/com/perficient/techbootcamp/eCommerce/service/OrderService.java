package com.perficient.techbootcamp.eCommerce.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.flowable.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.perficient.techbootcamp.eCommerce.entity.OrderItem;
import com.perficient.techbootcamp.eCommerce.entity.Orders;
import com.perficient.techbootcamp.eCommerce.entity.Product;
import com.perficient.techbootcamp.eCommerce.orders.OrderItems;
import com.perficient.techbootcamp.eCommerce.orders.PostOrderItem;
import com.perficient.techbootcamp.eCommerce.orders.ProductQuantity;
import com.perficient.techbootcamp.eCommerce.repository.OrderItemRepository;
import com.perficient.techbootcamp.eCommerce.repository.OrdersRepository;
import com.perficient.techbootcamp.eCommerce.repository.ProductRepository;

@Service
public class OrderService {
	
	@Autowired
	private OrdersRepository orderRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private RuntimeService runtimeService;
	
	public List<Orders> getAllOrders() {
		return (List<Orders>) orderRepository.findAll();
	}
	
	public OrderItems getAllOrderItems(Long orderId){
		Orders order = orderRepository.findById(orderId).orElseThrow();
		List<OrderItem> orderItems = orderItemRepository.findAllItemsByOrder(order);
		List<ProductQuantity> productQuantities = new ArrayList<ProductQuantity>();
		for(OrderItem item : orderItems) {
			productQuantities.add(
				new ProductQuantity(item.getProduct(), item.getQuantity())
			);
		}
		return new OrderItems(order, productQuantities);
	}
	
	public Orders placeOrder(List<PostOrderItem> items) {
		//Update Inventory
		LocalDate orderDate = LocalDate.now();
		LocalDate arrivalDate = orderDate.plus(Period.ofDays(5));
		Orders placedOrder = orderRepository.save(new Orders(orderDate, arrivalDate));
		Map<String, Object> variables = new HashMap<>();
		variables.put("arrivalDate", arrivalDate);
		runtimeService.startProcessInstanceById("placeOrder", variables);
		addOrderItems(placedOrder, items);
		return placedOrder;
	}
	
	private void addOrderItem(Orders order, Long productId, int quantityOrdered) {
		Product item = productRepository.findById(productId).orElseThrow();
		orderItemRepository.save(new OrderItem(order, item, quantityOrdered));
	}
		
	private void addOrderItems(Orders order, List<PostOrderItem> items) {
		for(PostOrderItem item : items) {
			addOrderItem(order, item.getProductId(), item.getQuantity());
		}
	}
	
	public void cancelOrder(Long orderId) throws IllegalArgumentException{
		orderRepository.deleteById(orderId);
	}
	
}
