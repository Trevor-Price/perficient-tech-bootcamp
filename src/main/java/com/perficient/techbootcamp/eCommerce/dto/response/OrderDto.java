package com.perficient.techbootcamp.ecommerce.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderDto {
    private Long id;
    private String orderDate;
    private String orderTime;
    private String status;
    private String expectedArrivalDate;
    private String expectedArrivalTime;
    private String cancelDate;
    private String cancelTime;
    private String actualArrivalDate;
    private String actualArrivalTime;
}