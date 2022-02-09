package com.perficient.techbootcamp.eCommerce.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	
	@Autowired
	private TaskService taskService;
	
	@Autowired
	private RepositoryService repoService;
	
	private Logger log = LogManager.getLogger(this.getClass());
	
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
	
	@Transactional
	public Orders placeOrder(List<PostOrderItem> items) {
		//Update Inventory
		LocalDateTime orderDate = LocalDateTime.now();
		LocalDateTime arrivalDate = orderDate.plusHours(1);
		
		Orders placedOrder = orderRepository.save(new Orders(orderDate, arrivalDate, true));
		Map<String, Object> variables = new HashMap<>();
		variables.put("arrivalDate", arrivalDate.toString());
		variables.put("order", placedOrder);
		runtimeService.startProcessInstanceByKey("placeOrder", variables);
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
	
	@Transactional
	public List<Task> getOrderTasks(){
		log.info("Process Definitions: " + repoService.createProcessDefinitionQuery().count());
		log.info("Current Tasks: " + taskService.createTaskQuery().count());
		return taskService.createTaskQuery().list();
	}
	
}
