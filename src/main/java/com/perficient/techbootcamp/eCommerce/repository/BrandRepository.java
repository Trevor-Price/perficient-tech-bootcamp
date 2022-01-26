package com.perficient.techbootcamp.eCommerce.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.perficient.techbootcamp.eCommerce.entity.Brand;

@Repository
public interface BrandRepository extends CrudRepository<Brand, Long>{
	
}
