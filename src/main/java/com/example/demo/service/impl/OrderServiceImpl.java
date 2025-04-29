package com.example.demo.service.impl;

import com.example.demo.mapstruct.OrderMapStruct;
import com.example.demo.model.dto.OrderDto;
import com.example.demo.model.entity.OrderEntity;
import com.example.demo.service.api.OrderService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class OrderServiceImpl implements OrderService {

    private AtomicLong nextOrderId = new AtomicLong(2003); // Start from 2003
    private Map<Long, OrderEntity> orderDatabase = new ConcurrentHashMap<>(); // In-memory "database"
    private final OrderMapStruct orderMapStruct;

    public OrderServiceImpl(OrderMapStruct orderMapStruct) {
        this.orderMapStruct = orderMapStruct;
    }

    @Override
    public OrderDto createOrder(Long userId, BigDecimal totalPrice, String orderStatus) {
        OrderEntity order = new OrderEntity();
        order.setOrderId(nextOrderId.getAndIncrement()); // Get the next available ID and increment the counter
        order.setUserId(userId);
        order.setTotalPrice(totalPrice);
        order.setOrderStatus(orderStatus);
        // Set createTime and updateTime
        Timestamp now = Timestamp.from(Instant.now());
        order.setCreateTime(now);
        order.setUpdateTime(now);
        // ... save the order to the database ...
        orderDatabase.put(order.getOrderId(), order); // Simulate saving to the database
        return orderMapStruct.orderEntityToOrderDto(order);
    }

    @Override
    public OrderDto getOrderById(Long orderId) {
        OrderEntity orderEntity = orderDatabase.get(orderId);
        return orderMapStruct.orderEntityToOrderDto(orderEntity);
    }

    @Override
    public List<OrderDto> getOrdersByUserId(Long userId) {
        List<OrderEntity> userOrders = new ArrayList<>();
        for (OrderEntity order : orderDatabase.values()) {
            if (order.getUserId().equals(userId)) {
                userOrders.add(order);
            }
        }
        return orderMapStruct.orderEntityListToOrderDtoList(userOrders);
    }

    @Override
    public List<OrderDto> getAllOrders() {
        return orderMapStruct.orderEntityListToOrderDtoList(new ArrayList<>(orderDatabase.values()));
    }

    @Override
    public OrderDto updateOrder(OrderDto orderDto) {
        OrderEntity order = orderMapStruct.orderDtoToOrderEntity(orderDto);
        if(orderDatabase.containsKey(order.getOrderId())){
            // update updateTime
            order.setUpdateTime(Timestamp.from(Instant.now()));
            orderDatabase.put(order.getOrderId(), order);
            return orderMapStruct.orderEntityToOrderDto(order);
        }
        return null;
    }

    @Override
    public void deleteOrder(Long orderId) {
        orderDatabase.remove(orderId);
    }
}
