package com.perficient.techbootcamp.ecommerce.response;

import com.perficient.techbootcamp.ecommerce.entity.Product;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor @RequiredArgsConstructor @ToString
public class ProductQuantity {

	private @NonNull @Getter @Setter Product product;
	private @NonNull @Getter @Setter Integer quantity;

}
