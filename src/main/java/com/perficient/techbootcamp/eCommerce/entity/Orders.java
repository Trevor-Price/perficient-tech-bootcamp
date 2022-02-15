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

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@RequiredArgsConstructor
@NoArgsConstructor
@ToString
@Table(schema = "ecommerce")
public class Orders implements Serializable{
	
	private static final long serialVersionUID = 4789751747228323912L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY) 
		private @Getter @Setter Long orderId;
	
	private @NonNull @Getter @Setter LocalDateTime orderDate;
	private @NonNull @Getter @Setter LocalDateTime expectedArrivalDate;
	private @Getter @Setter LocalDateTime cancelDate;

	@Enumerated(EnumType.STRING)
		private @NonNull @Getter @Setter OrderStatus orderStatus;
	
	@OneToMany(
		mappedBy = "order",
		cascade = CascadeType.ALL,
		orphanRemoval = true
	)
	private List<OrderItem> orderItems;
	
}
