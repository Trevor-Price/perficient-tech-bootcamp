package com.perficient.techbootcamp.ecommerce.dto.mapper;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import com.perficient.techbootcamp.ecommerce.dto.response.OrderDto;
import com.perficient.techbootcamp.ecommerce.entity.Orders;

public class OrderDtoMapper {
    public static OrderDto toOrderDto(Orders order){
        return new OrderDto()
            .setId(order.getOrderId())
            .setOrderDate(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).format(order.getOrderDateTime()))
            .setOrderTime(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT).format(order.getOrderDateTime()))
            .setExpectedArrivalDate(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).format(order.getExpectedArrivalDateTime()))
            .setExpectedArrivalTime(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT).format(order.getExpectedArrivalDateTime()))
            .setStatus(order.getOrderStatus().name())
            .setCancelDate((order.getCancelDateTime().isPresent()) 
                ? DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).format(order.getCancelDateTime().get())
                : null
            )
            .setCancelTime((order.getCancelDateTime().isPresent()) 
                ? DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT).format(order.getCancelDateTime().get())
                : null
            )
            .setActualArrivalDate((order.getActualArrivalDateTime().isPresent()) 
                ? DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).format(order.getActualArrivalDateTime().get())
                : null
            )
            .setActualArrivalTime((order.getActualArrivalDateTime().isPresent()) 
                ? DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT).format(order.getActualArrivalDateTime().get())
                : null
            );
    }
}
