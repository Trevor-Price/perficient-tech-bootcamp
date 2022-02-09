package com.perficient.techbootcamp.eCommerce.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(schema = "ecommerce")
public class Brand implements Serializable{
	
	private static final long serialVersionUID = 5447891919722293324L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long brandId;
	
	private String name;
	private String logo;
	
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
	
	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}
	
}
