package com.perficient.techbootcamp.ecommerce.dto.mapper;

import com.perficient.techbootcamp.ecommerce.dto.response.OrderDto;
import com.perficient.techbootcamp.ecommerce.entity.Orders;

public class OrderDtoMapper {
    public static OrderDto toOrderDto(Orders order){
        return new OrderDto()
            .setId(order.getOrderId())
            .setOrderDateTime(order.getOrderDateTime().toString())
            .setExpectedArrivalDateTime(order.getExpectedArrivalDateTime().toString())
            .setStatus(order.getOrderStatus().name())
            .setCancelDateTime((order.getCancelDateTime().isPresent()) 
                ? order.getCancelDateTime().get().toString()
                : null
            )
            .setActualArrivalDateTime((order.getActualArrivalDateTime().isPresent()) 
                ? order.getActualArrivalDateTime().get().toString()
                : null
            );
    }
}
