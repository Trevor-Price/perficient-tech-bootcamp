package com.perficient.techbootcamp.ecommerce.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.perficient.techbootcamp.ecommerce.dto.mapper.OrderDtoMapper;
import com.perficient.techbootcamp.ecommerce.dto.mapper.OrderItemDtoMapper;
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
import com.perficient.techbootcamp.ecommerce.service.exception.InvalidOrderStatusException;
import com.perficient.techbootcamp.ecommerce.service.exception.OrderNotFoundException;
import com.perficient.techbootcamp.ecommerce.service.exception.ProductNotFoundException;

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
	 *  Get All Orders
	 */
	public List<OrderDto> getAllOrders() {
		List<Orders> orders = (List<Orders>) orderRepository.findAll();
		return orders.stream()
			.map(order -> OrderDtoMapper.toOrderDto(order))
			.toList();
	}
	
	/**
	 * Get Order
	 */
	public OrderDto getOrder(Long orderId) {
		Optional<Orders> order = orderRepository.findById(orderId);
		if(order.isPresent()){
			return OrderDtoMapper.toOrderDto(order.get());
		}
		throw new OrderNotFoundException();
	}

	/**
	 * Get Order Items
	 */
	public List<OrderItemDto> getAllOrderItems(Long orderId){
		List<OrderItem> orderItems = orderItemRepository.findAllOrderItemsByOrderId(orderId);
		return orderItems.stream()
			.map(orderItem -> OrderItemDtoMapper.toOrderItemDto(orderItem))
			.toList();
	}
	
	/**
	 * Place Order
	 */
	public OrderDto placeOrder(List<PlaceNewOrderItemDto> items) {
		LocalDateTime orderDate = LocalDateTime.now();
		LocalDateTime expectedArrivalDate = orderDate.plusDays(2);
		Orders placedOrder = orderRepository.save(
			new Orders(orderDate, expectedArrivalDate, OrderStatus.PLACED)
		);
		addOrderItems(placedOrder, items);
		return OrderDtoMapper.toOrderDto(placedOrder);
	}

		private void addOrderItems(Orders order, List<PlaceNewOrderItemDto> newOrderItems) {
			newOrderItems.stream().forEach(
				orderItem -> {
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
			);
		}
	
	/**
	 * Update Order Status
	 */
	public OrderDto updateOrderStatus(Long orderId, String orderStatus){
		Optional<Orders> order = orderRepository.findById(orderId);
		if(order.isPresent()){
			Orders updatedOrder = order.get();
			try{
				updatedOrder.setOrderStatus(OrderStatus.valueOf(orderStatus));
			} catch(IllegalArgumentException | NullPointerException e){
				throw new InvalidOrderStatusException();
			}
			switch(OrderStatus.valueOf(orderStatus)){
				case ARRIVED: 
					updatedOrder.setActualArrivalDateTime(LocalDateTime.now());
				break;
				case CANCELLED: 
					restockProducts(updatedOrder);
					updatedOrder.setCancelDateTime(LocalDateTime.now());
				break;
				default: break;
			}
			return OrderDtoMapper.toOrderDto(orderRepository.save(updatedOrder));
		}
		throw new OrderNotFoundException();
	}

		/**
		 * Restock Products for cancelled Order
		 * @param order
		 */
		private void restockProducts(Orders order){
			List<OrderItem> orderItems = orderItemRepository.findAllOrderItemsByOrderId(order.getOrderId());
			int updatedAvailableQuantity;
			for(OrderItem item : orderItems){
				Optional<Product> product = productRepository.findById(item.getProduct().getProductId());
				if(product.isPresent()){
					Product restockedProduct = product.get();
					updatedAvailableQuantity = item.getQuantity() + restockedProduct.getQuantityAvailable();
					restockedProduct.setQuantityAvailable(updatedAvailableQuantity);
					productRepository.save(restockedProduct);
					return;
				}
				throw new ProductNotFoundException();
			}
		}

}
