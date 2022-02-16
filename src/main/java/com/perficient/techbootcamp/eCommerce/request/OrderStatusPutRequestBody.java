package com.perficient.techbootcamp.ecommerce.request;

import com.perficient.techbootcamp.ecommerce.entity.OrderStatus;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor @RequiredArgsConstructor @ToString
public class OrderStatusPutRequestBody {
    
    private @NonNull @Getter @Setter OrderStatus orderStatus;

}
