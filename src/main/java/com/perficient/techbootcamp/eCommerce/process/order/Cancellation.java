package com.perficient.techbootcamp.eCommerce.process.order;

import java.time.LocalDateTime;

import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;

import com.perficient.techbootcamp.eCommerce.entity.Orders;
import com.perficient.techbootcamp.eCommerce.repository.OrdersRepository;

public class Cancellation implements JavaDelegate{

	@Autowired
	private OrdersRepository orderRepository;
	
	@Override
	public void execute(DelegateExecution execution) {
		Orders order = (Orders) execution.getVariable("order");
		order.setCancelDate(LocalDateTime.now());
		orderRepository.save(order);
	}

}
