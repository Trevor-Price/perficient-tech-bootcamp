package com.perficient.techbootcamp.eCommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.perficient.techbootcamp.eCommerce.entity.OrderItem;
import com.perficient.techbootcamp.eCommerce.entity.Orders;

@Repository
public interface OrderItemRepository extends CrudRepository<OrderItem, Long>{

	@Query(
		"Select oi From OrderItem oi Where oi.order=?1"
	)
	public List<OrderItem> findAllItemsByOrder(Orders order);
	
}
