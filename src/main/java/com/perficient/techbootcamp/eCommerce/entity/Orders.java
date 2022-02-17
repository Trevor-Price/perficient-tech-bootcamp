package com.perficient.techbootcamp.ecommerce.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.NonNull;

@Entity
@Data
@Table(schema = "ecommerce")
public class Orders implements Serializable{
	
	private static final long serialVersionUID = 4789751747228323912L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY) 
		private Long orderId;
	
	private @NonNull LocalDateTime orderDate;
	private @NonNull LocalDateTime expectedArrivalDate;
	private LocalDateTime cancelDate;

	@Enumerated(EnumType.STRING)
		private @NonNull OrderStatus orderStatus;
	
	@OneToMany(
		mappedBy = "order",
		cascade = CascadeType.ALL,
		orphanRemoval = true
	)
	private List<OrderItem> orderItems;
	
}
