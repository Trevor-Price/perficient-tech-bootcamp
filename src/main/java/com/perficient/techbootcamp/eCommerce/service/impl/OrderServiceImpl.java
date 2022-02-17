package com.perficient.techbootcamp.ecommerce.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.perficient.techbootcamp.ecommerce.dto.request.PlaceNewOrderItemDto;
import com.perficient.techbootcamp.ecommerce.dto.response.OrderDto;
import com.perficient.techbootcamp.ecommerce.dto.response.OrderItemDto;
import com.perficient.techbootcamp.ecommerce.entity.OrderItem;
import com.perficient.techbootcamp.ecommerce.entity.OrderStatus;
import com.perficient.techbootcamp.ecommerce.entity.Orders;
import com.perficient.techbootcamp.ecommerce.entity.Product;
import com.perficient.techbootcamp.ecommerce.repository.OrderItemRepository;
import com.perficient.techbootcamp.ecommerce.repository.OrdersRepository;
import com.perficient.techbootcamp.ecommerce.repository.ProductRepository;
import com.perficient.techbootcamp.ecommerce.service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrdersRepository orderRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	/**
	 *  Get Order Logic
	 */
	public List<OrderDto> getAllOrders() {
		List<Orders> orders = (List<Orders>) orderRepository.findAll();
		//Map to dto
		return orders;
	}
	
	/**
	 * Get Order Items Logic
	 */
	public List<OrderItemDto> getAllOrderItems(Long orderId){
		List<OrderItem> orderItems = orderItemRepository.findAllItemsByOrderId(orderId);
		//Map to dto
		return orderItems;
	}
	
	/**
	 * Place Order Logic
	 */
	public OrderDto placeOrder(List<PlaceNewOrderItemDto> items) {
		LocalDateTime orderDate = LocalDateTime.now();
		LocalDateTime expectedArrivalDate = orderDate.plusDays(1);
		Orders placedOrder = orderRepository.save(
			new Orders(orderDate, expectedArrivalDate, OrderStatus.PLACED)
		);
		addOrderItems(placedOrder, items);
		//Map to dto
		return placedOrder;
	}

		private void addOrderItems(Orders order, List<PlaceNewOrderItemDto> newOrderItems) {
			for(PlaceNewOrderItemDto orderItem : newOrderItems) {
				Product product = productRepository.findById(orderItem.getProductId()).orElseThrow();
				int quantityAvailable = product.getQuantityAvailable().intValue();
				int updatedAvailableQuantity = 0;
				int quantityOrdered = orderItem.getQuantity();
				if(quantityOrdered <= quantityAvailable){
					updatedAvailableQuantity = quantityAvailable - quantityOrdered;
				} else {
					quantityOrdered = quantityAvailable;
				}
				product.setQuantityAvailable(updatedAvailableQuantity);
				productRepository.save(product);
				orderItemRepository.save(new OrderItem(order, product, quantityOrdered));
			}
		}
	
	/**
	 * Update Order Status
	 */
	public void updateOrderStatus(Long orderId, String orderStatus) {
		Orders updatedOrder = orderRepository.findById(orderId).orElseThrow();
		updatedOrder.setOrderStatus(OrderStatus.valueOf(orderStatus));
		orderRepository.save(updatedOrder);
	}

	/**
	 * Cancel Order Logic
	 */
	public void cancelOrder(Long orderId) throws IllegalArgumentException{
		Orders cancelledOrder = orderRepository.findById(orderId).orElseThrow();
		restockProducts(cancelledOrder);
		cancelledOrder.setCancelDate(LocalDateTime.now());
		cancelledOrder.setOrderStatus(OrderStatus.CANCELLED);
		orderRepository.save(cancelledOrder);
	}

		private void restockProducts(Orders order){
			List<OrderItemDto> orderItems = orderItemRepository.findAllItemsByOrder(order);
			Product product;

			int updatedAvailableQuantity;

			for(OrderItemDto item : orderItems){
				product = productRepository.findById(item.getProduct().getProductId()).orElseThrow();
				updatedAvailableQuantity = item.getQuantity() + product.getQuantityAvailable();
				product.setQuantityAvailable(updatedAvailableQuantity);
				productRepository.save(product);
			}
		}

}
