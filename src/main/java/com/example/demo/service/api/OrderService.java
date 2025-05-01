package com.example.demo.service.api;

import com.example.demo.model.dto.OrderDto;
import com.example.demo.model.dto.PageInfoDto;
import com.example.demo.model.vo.PageInfo;

import java.math.BigDecimal;
import java.util.List;

public interface OrderService extends BaseService<OrderDto>{

    OrderDto createOrder(String userId, BigDecimal totalPrice, String orderStatus);

    List<OrderDto> getOrdersByUserId(String userId);


    PageInfo<OrderDto> getAllOrdersWithPagination(PageInfoDto<OrderDto> pageInfoDto);

}
