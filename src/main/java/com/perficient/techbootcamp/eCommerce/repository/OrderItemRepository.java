package com.perficient.techbootcamp.ecommerce.repository;

import java.util.List;

import com.perficient.techbootcamp.ecommerce.entity.OrderItem;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends CrudRepository<OrderItem, Long>{

	@Query(
		"Select oi From OrderItem oi Where oi.orderId=?1"
	)
	public List<OrderItem> findAllItemsByOrderId(Long orderId);
	
}
