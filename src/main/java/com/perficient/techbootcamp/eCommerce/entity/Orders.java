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
	
	private @NonNull LocalDateTime orderDateTime;
	private @NonNull LocalDateTime expectedArrivalDateTime;

	@Column(nullable = true)
		private LocalDateTime cancelDateTime;

	@Column(nullable = true)
		private LocalDateTime actualArrivalDateTime;

	@Enumerated(EnumType.STRING)
		private @NonNull OrderStatus orderStatus;
	
	@OneToMany(
		mappedBy = "order",
		cascade = CascadeType.ALL,
		orphanRemoval = true
	)
	private List<OrderItem> orderItems;
	
	/**
	 * Wraps the CancelDateTime field in an Optional object and returns it to the caller. 
	 * It is wrapped in this object since an order might not be cancelled and thus return null.
	 * @return
	 */
	public Optional<LocalDateTime> getCancelDateTime(){
		return Optional.ofNullable(cancelDateTime);
	}

	/**
	 * Wraps the actualArrivalDateTime field in an Optional object and returns it to the caller. 
	 * It is wrapped in this object since an order might not arrive and thus return null.
	 * @return
	 */
	public Optional<LocalDateTime> getActualArrivalDateTime(){
		return Optional.ofNullable(actualArrivalDateTime);
	}
}
