package com.example.demo.service.impl;

import com.example.demo.dao.OrderDao;
import com.example.demo.mapstruct.OrderMapStruct;
import com.example.demo.model.dto.OrderDto;
import com.example.demo.model.entity.OrderEntity;
import com.example.demo.model.dto.PageInfoDto;
import com.example.demo.model.vo.PageInfo;
import com.example.demo.service.api.OrderService;
import com.example.demo.service.api.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;

import java.util.stream.Collectors;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;


@Slf4j
@Service
public class OrderServiceImpl implements OrderService, BaseService<OrderDto> {


    @Autowired
    private OrderDao orderDao;

    @Autowired
    private OrderMapStruct orderMapStruct;
    private AtomicLong nextOrderId = new AtomicLong(2003); // Start from 2003

    public OrderServiceImpl(OrderMapStruct orderMapStruct) {
        this.orderMapStruct = orderMapStruct;
    }

    public OrderDto createOrder(String userId, BigDecimal totalPrice, String orderStatus) {
        log.info("Creating a new order for userId: {}, totalPrice: {}, orderStatus: {}", userId, totalPrice, orderStatus);
        OrderEntity order = new OrderEntity();
        order.setOrderId(getNextOrderId());
        order.setUserId(userId);
        order.setTotalPrice(totalPrice);
        order.setOrderStatus(orderStatus);
        // Set createTime and updateTime
        Timestamp now = Timestamp.from(Instant.now());
        order.setCreateTime(now);
        order.setUpdateTime(now);
        orderDao.insert(order);
        log.info("Order created successfully with orderId: {}", order.getOrderId());
        return orderMapStruct.orderEntityToOrderDto(order);
    }

    @Override
    @Override
    public OrderDto getById(String orderId) {
        log.info("Getting order by orderId: {}", orderId);
        OrderEntity orderEntity = orderDao.getById(orderId);
        return orderMapStruct.orderEntityToOrderDto(orderEntity);
    }

    @Override
    public List<OrderDto> getOrdersByUserId(String userId) {
        List<OrderEntity> userOrders = orderDao.getAll().stream().filter(order -> order.getUserId().equals(userId)).toList();

        log.info("Getting orders by userId: {}, found {} orders", userId, userOrders.size());

        return orderMapStruct.orderEntityListToOrderDtoList(userOrders);

    }

    @Override
    public List<OrderDto> getAll() {
        log.info("Getting all orders");
        List<OrderEntity> orderEntities = orderDao.getAll();
        return orderMapStruct.orderEntityListToOrderDtoList(orderEntities);
    }

    @Override
    public void update(OrderDto orderDto) {
        log.info("Updating order with orderId: {}", orderDto.getOrderId());
        OrderEntity orderEntity = orderMapStruct.orderDtoToOrderEntity(orderDto);
        orderDao.update(orderEntity);
        log.info("Order updated successfully with orderId: {}", orderDto.getOrderId());
    }

    @Override
    public void delete(String orderId) {
        log.info("Deleting order with orderId: {}", orderId);
        orderDao.delete(orderId);
    }

    @Override
    public void insert(OrderDto orderDto) {
        log.info("Inserting new order with orderId: {}", orderDto.getOrderId());
        OrderEntity orderEntity = orderMapStruct.orderDtoToOrderEntity(orderDto);
        orderDao.insert(orderEntity);
    }

    @Override
    public PageInfo<OrderDto> getAllOrdersWithPagination(PageInfoDto<OrderDto> pageInfoDto) {
        log.info("Starting to get all orders with pagination, pageInfoDto: {}", pageInfoDto);

        int pageNumber = pageInfoDto.getPageNumber();
        int pageSize = pageInfoDto.getPageSize();
        OrderDto orderDto = pageInfoDto.getQuery();

        // 将 OrderDto 转换为 OrderEntity
        OrderEntity orderEntity = null;
        if (orderDto != null) {
            orderEntity = orderMapStruct.orderDtoToOrderEntity(orderDto);
        }
        List<OrderEntity> orderEntities = orderDao.getAllOrdersWithPagination(orderEntity, pageNumber, pageSize);
        List<OrderDto> orderDtos = orderEntities.stream().map(orderMapStruct::orderEntityToOrderDto).collect(Collectors.toList());
        return new PageInfo<>(pageNumber, pageSize, orderDao.countByEntity(orderEntity), orderDtos);
    }

    private String getNextOrderId() {
        return "ORDER_" + nextOrderId.getAndIncrement();
    }
}
