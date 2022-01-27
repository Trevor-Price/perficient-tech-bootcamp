package com.perficient.techbootcamp.eCommerce.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.perficient.techbootcamp.eCommerce.controller.requestbody.PostOrderItem;
import com.perficient.techbootcamp.eCommerce.entity.OrderItem;
import com.perficient.techbootcamp.eCommerce.entity.Orders;
import com.perficient.techbootcamp.eCommerce.entity.Product;
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
	
	public List<Orders> getAllOrders() {
		return (List<Orders>) orderRepository.findAll();
	}
	
	public List<OrderItem> getAllOrderItems(Long orderId){
		Orders order = orderRepository.findById(orderId).orElseThrow();
		List<OrderItem> orderItems = orderItemRepository.findAllItemsByOrder(order);
		return orderItems;
	}
	
	public Orders placeOrder(List<PostOrderItem> items) {
		LocalDate orderDate = LocalDate.now();
		LocalDate arrivalDate = orderDate.plus(Period.ofDays(5));
		Orders placedOrder = orderRepository.save(new Orders(orderDate, arrivalDate));
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
			//Return accepted status
		}
	
	public void cancelOrder(Long orderId) throws IllegalArgumentException{
		orderRepository.deleteById(orderId);
	}
	
}
