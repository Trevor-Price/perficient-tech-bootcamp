package com.perficient.techbootcamp.ecommerce.repository;

import com.perficient.techbootcamp.ecommerce.entity.Orders;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersRepository extends CrudRepository<Orders, Long>{
	
}
