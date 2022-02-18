package com.perficient.techbootcamp.ecommerce.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

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
@Table(schema = "ecommerce", uniqueConstraints= {
	@UniqueConstraint(columnNames = {"order_id", "product_id"})
})
public class OrderItem implements Serializable{
	
	private static final long serialVersionUID = 5350593550367363387L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long orderItemId;
	
	@ManyToOne @JoinColumn(name = "order_id")
		private @NonNull Orders order;
	
	@ManyToOne @JoinColumn(name = "product_id")
		private @NonNull Product product;
	
	private @NonNull Integer quantity;
	
}
