package com.perficient.techbootcamp.eCommerce.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.perficient.techbootcamp.eCommerce.entity.Orders;

@Repository
public interface OrdersRepository extends CrudRepository<Orders, Long>{
	
}
