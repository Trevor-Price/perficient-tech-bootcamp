package com.perficient.techbootcamp.ecommerce.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor @RequiredArgsConstructor @ToString
public class OrderItemPostRequestBody {

	private @NonNull @Getter @Setter Long productId;
	private @NonNull @Getter @Setter Integer quantity;
	
}
