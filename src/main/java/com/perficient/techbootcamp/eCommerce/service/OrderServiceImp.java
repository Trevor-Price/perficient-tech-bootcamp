package com.perficient.techbootcamp.ecommerce.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.perficient.techbootcamp.ecommerce.entity.OrderItem;
import com.perficient.techbootcamp.ecommerce.entity.OrderStatus;
import com.perficient.techbootcamp.ecommerce.entity.Orders;
import com.perficient.techbootcamp.ecommerce.entity.Product;
import com.perficient.techbootcamp.ecommerce.repository.OrderItemRepository;
import com.perficient.techbootcamp.ecommerce.repository.OrdersRepository;
import com.perficient.techbootcamp.ecommerce.repository.ProductRepository;
import com.perficient.techbootcamp.ecommerce.request.OrderItemPostRequestBody;
import com.perficient.techbootcamp.ecommerce.response.OrderItemsResponseBody;
import com.perficient.techbootcamp.ecommerce.response.ProductQuantity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImp implements OrderService {
	
	@Autowired
	private OrdersRepository orderRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	/**
	 *  Get Order Logic
	 */
	public List<Orders> getAllOrders() {
		return (List<Orders>) orderRepository.findAll();
	}
	
	/**
	 * Get Order Items Logic
	 */
	public OrderItemsResponseBody getAllOrderItems(Long orderId){
		Orders order = orderRepository.findById(orderId).orElseThrow();
		List<OrderItem> orderItems = orderItemRepository.findAllItemsByOrder(order);
		List<ProductQuantity> productQuantities = new ArrayList<ProductQuantity>();
		for(OrderItem item : orderItems) {
			productQuantities.add(
				new ProductQuantity(item.getProduct(), item.getQuantity())
			);
		}
		return new OrderItemsResponseBody(order, productQuantities);
	}
	
	/**
	 * Place Order Logic
	 */
	public Orders placeOrder(List<OrderItemPostRequestBody> items) {
		LocalDateTime orderDate = LocalDateTime.now();
		LocalDateTime expectedArrivalDate = orderDate.plusHours(1);
		Orders placedOrder = orderRepository.save(
			new Orders(orderDate, expectedArrivalDate, OrderStatus.PLACED)
		);
		addOrderItems(placedOrder, items);
		return placedOrder;
	}

		private void addOrderItems(Orders order, List<OrderItemPostRequestBody> items) {
			for(OrderItemPostRequestBody item : items) {
				addOrderItem(order, item.getProductId(), item.getQuantity());
			}
		}

		private void addOrderItem(Orders order, Long productId, Integer quantity) {
			Product item = productRepository.findById(productId).orElseThrow();
			int quantityAvailable = item.getQuantityAvailable().intValue();
			int updatedAvailableQuantity;
			int quantityOrdered = quantity.intValue();
			if(quantityOrdered <= quantityAvailable){
				updatedAvailableQuantity = quantityAvailable - quantityOrdered;
			} else {
				quantityOrdered = quantityAvailable;
				updatedAvailableQuantity = 0;
			}
			item.setQuantityAvailable(updatedAvailableQuantity);
			productRepository.save(item);
			orderItemRepository.save(new OrderItem(order, item, quantityOrdered));
		}
	
	/**
	 * Update Order Status
	 */
	public void updateOrderStatus(Long orderId, OrderStatus orderStatus) {
		Orders updatedOrder = orderRepository.findById(orderId).orElseThrow();
		updatedOrder.setOrderStatus(orderStatus);
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
			List<OrderItem> orderItems = orderItemRepository.findAllItemsByOrder(order);
			Product product;

			int updatedAvailableQuantity;

			for(OrderItem item : orderItems){
				product = productRepository.findById(item.getProduct().getProductId()).orElseThrow();
				updatedAvailableQuantity = item.getQuantity() + product.getQuantityAvailable();
				product.setQuantityAvailable(updatedAvailableQuantity);
				productRepository.save(product);
			}
		}

}
