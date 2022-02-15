package com.perficient.techbootcamp.ecommerce.repository;

import com.perficient.techbootcamp.ecommerce.entity.Brand;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends CrudRepository<Brand, Long>{
	
}
