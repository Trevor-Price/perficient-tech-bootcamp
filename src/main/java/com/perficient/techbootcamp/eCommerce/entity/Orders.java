package com.perficient.techbootcamp.ecommerce.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
public class Orders implements Serializable{
	
	private static final long serialVersionUID = 4789751747228323912L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY) 
		private Long orderId;
	
	private @NonNull LocalDateTime orderDate;
	private @NonNull LocalDateTime expectedArrivalDate;

	@Column(nullable = true)
		private LocalDateTime cancelDate;

	@Enumerated(EnumType.STRING)
		private @NonNull OrderStatus orderStatus;
	
	@OneToMany(
		mappedBy = "order",
		cascade = CascadeType.ALL,
		orphanRemoval = true
	)
	private List<OrderItem> orderItems;
	
	/**
	 * Wraps the CancelDate field in an Optional object and returns it to the caller. 
	 * It is wrapped in this object since an order might not be cancelled.
	 * @return
	 */
	public Optional<LocalDateTime> getCancelDate(){
		return Optional.ofNullable(cancelDate);
	}
}
