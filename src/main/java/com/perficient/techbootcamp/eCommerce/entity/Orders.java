package com.perficient.techbootcamp.eCommerce.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(schema = "ecommerce")
public class Orders implements Serializable{
	
	private static final long serialVersionUID = 4789751747228323912L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Long orderId;
	
	private LocalDateTime orderDate;
	private LocalDateTime arrivalDate;
	private LocalDateTime cancelDate;
	private Boolean hasShipped;
	private Boolean hasArrived;
	
	@OneToMany(
		mappedBy = "order",
		cascade = CascadeType.ALL,
		orphanRemoval = true
	)
	private List<OrderItem> orderItems;
	
	public Orders() {
		super();
	}
	
	public Orders(LocalDateTime orderDate, LocalDateTime arrivalDate, Boolean hasShipped) {
		super();
		this.orderDate = orderDate;
		this.arrivalDate = arrivalDate;
		this.hasShipped = hasShipped;
		this.hasArrived = false;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	
	public LocalDateTime getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDateTime orderDate) {
		this.orderDate = orderDate;
	}

	public LocalDateTime getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(LocalDateTime arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public Boolean getHasShipped() {
		return hasShipped;
	}

	public void setHasShipped(Boolean hasShipped) {
		this.hasShipped = hasShipped;
	}

	public Boolean getHasArrived() {
		return hasArrived;
	}

	public void setHasArrived(Boolean hasArrived) {
		this.hasArrived = hasArrived;
	}

	public LocalDateTime getCancelDate() {
		return cancelDate;
	}

	public void setCancelDate(LocalDateTime cancelDate) {
		this.cancelDate = cancelDate;
	}
	
}
