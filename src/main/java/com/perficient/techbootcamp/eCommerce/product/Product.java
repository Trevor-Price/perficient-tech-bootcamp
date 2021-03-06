package com.perficient.techbootcamp.eCommerce.product;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Product implements Serializable{
	
	private static final long serialVersionUID = 2240373165407600405L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long productId;
	
	private String description;
	private double price;
	private int quantityAvailable;
	
	@ManyToOne
	@JoinColumn(name = "brand_id", nullable=true)
	private Brand brand;
	
	private String imageUrl;
	
	public Product() {
		super();
	}
	
	public Product(String description, double price, int quantityAvailable) {
		this.description = description;
		this.price = price;
		this.quantityAvailable = quantityAvailable;
	}

	public Product(String description, double price, int quantityAvailable, Brand brand) {
		this.description = description;
		this.price = price;
		this.quantityAvailable = quantityAvailable;
		this.brand = brand;
	}
	
	public Product(String description, double price, int quantityAvailable, Brand brand, String imageUrl) {
		this.description = description;
		this.price = price;
		this.quantityAvailable = quantityAvailable;
		this.brand = brand;
		this.imageUrl = imageUrl;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantityAvailable() {
		return quantityAvailable;
	}

	public void setQuantityAvailable(int quantityAvailable) {
		this.quantityAvailable = quantityAvailable;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}
	
}
