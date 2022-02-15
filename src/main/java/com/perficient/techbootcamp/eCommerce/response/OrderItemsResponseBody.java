package com.perficient.techbootcamp.ecommerce.response;

import java.util.List;

import com.perficient.techbootcamp.ecommerce.entity.Orders;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor @RequiredArgsConstructor @ToString
public class OrderItemsResponseBody {
	
	private @NonNull @Getter @Setter Orders order;
	private @NonNull @Getter @Setter List<ProductQuantity> productQuantities;

}
