package com.example.demo.model.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data 
public class OrderDto extends BaseDto{
    private Long orderId;
    private Long userId;
    private BigDecimal totalPrice;
    private String orderStatus;

}
