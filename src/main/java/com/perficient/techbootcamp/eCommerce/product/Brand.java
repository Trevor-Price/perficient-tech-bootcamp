package com.perficient.techbootcamp.eCommerce.product;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Brand implements Serializable{
	
	private static final long serialVersionUID = 5447891919722293324L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long brandId;
	
	private String name;
	
	@OneToMany(mappedBy="brand")
	private Set<Product> products;
	
	public Brand() {
		super();
	}
	
	public Brand(String name) {
		super();
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public long getBrandId() {
		return brandId;
	}

	public void setBrandId(long brandId) {
		this.brandId = brandId;
	}
	
}
