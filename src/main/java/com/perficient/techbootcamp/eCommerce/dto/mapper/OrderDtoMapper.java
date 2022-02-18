package com.perficient.techbootcamp.ecommerce.dto.mapper;

import com.perficient.techbootcamp.ecommerce.dto.response.OrderDto;
import com.perficient.techbootcamp.ecommerce.entity.Orders;

public class OrderDtoMapper {
    public static OrderDto toOrderDto(Orders order){
        return new OrderDto()
            .setId(order.getOrderId())
            .setOrderDate(order.getOrderDate().toString())
            .setExpectedArrivalDate(order.getExpectedArrivalDate().toString())
            .setOrderStatus(order.getOrderStatus().name())
            .setCancelDate((order.getCancelDate().isPresent()) 
                ? order.getCancelDate().get().toString() 
                : null
            );
    }
}
