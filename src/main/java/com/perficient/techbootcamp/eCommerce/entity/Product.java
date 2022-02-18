package com.perficient.techbootcamp.ecommerce.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@RequiredArgsConstructor
@Table(schema = "ecommerce")
public class Product implements Serializable{
	
	private static final long serialVersionUID = 2240373165407600405L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
		private long productId;
	
	private @NonNull String description;
	private @NonNull Double price;
	private @NonNull Integer quantityAvailable;
	
	@ManyToOne @JoinColumn(name = "brand_id", nullable=true)
		private @NonNull Brand brand;
}
