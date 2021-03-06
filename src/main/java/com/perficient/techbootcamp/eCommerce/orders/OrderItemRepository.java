package com.perficient.techbootcamp.eCommerce.orders;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends CrudRepository<OrderItem, Long>{

	@Query(
		"Select oi From OrderItem oi Where oi.order=?1"
	)
	public List<OrderItem> findAllItemsByOrder(Orders order);
	
}
