package com.example.demo.model.vo.order;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderVo {
    private String orderId;
    private String userId;
    private BigDecimal totalPrice;
    private String orderStatus;
}