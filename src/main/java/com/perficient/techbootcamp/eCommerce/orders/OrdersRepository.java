package com.perficient.techbootcamp.eCommerce.orders;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersRepository extends CrudRepository<Orders, Long>{
	
}
