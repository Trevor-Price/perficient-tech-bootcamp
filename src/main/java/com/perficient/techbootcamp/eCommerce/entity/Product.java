package com.perficient.techbootcamp.ecommerce.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@RequiredArgsConstructor
@Table(schema = "ecommerce")
public class Product implements Serializable{
	
	private static final long serialVersionUID = 2240373165407600405L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
		private @Getter @Setter long productId;
	
	private @NonNull @Getter @Setter String description;
	private @NonNull @Getter @Setter Double price;
	private @NonNull @Getter @Setter Integer quantityAvailable;
	
	@ManyToOne @JoinColumn(name = "brand_id", nullable=true)
		private @NonNull @Getter @Setter Brand brand;
	
	private @Getter @Setter String imageUrl;
}
