package com.example.demo.service.api;

import com.example.demo.model.dto.OrderDto;

import java.math.BigDecimal;
import java.util.List;

public interface OrderService {

    OrderDto createOrder(Long userId, BigDecimal totalPrice, String orderStatus);

    OrderDto getOrderById(Long orderId);

    List<OrderDto> getOrdersByUserId(Long userId);

    List<OrderDto> getAllOrders();

    OrderDto updateOrder(OrderDto orderDto);

    void deleteOrder(Long orderId);
}
