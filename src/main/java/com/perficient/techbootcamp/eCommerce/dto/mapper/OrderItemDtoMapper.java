package com.perficient.techbootcamp.ecommerce.dto.mapper;

import com.perficient.techbootcamp.ecommerce.dto.response.OrderItemDto;
import com.perficient.techbootcamp.ecommerce.entity.OrderItem;

public class OrderItemDtoMapper {
 
    public static OrderItemDto toOrderItemDto (OrderItem orderItem){
        return new OrderItemDto()
            .setProductId(orderItem.getProduct().getProductId())
            .setDescription(orderItem.getProduct().getDescription())
            .setBrand(orderItem.getProduct().getBrand().getName())
            .setPrice(orderItem.getProduct().getPrice())
            .setQuantity(orderItem.getQuantity());
    }
    
}
