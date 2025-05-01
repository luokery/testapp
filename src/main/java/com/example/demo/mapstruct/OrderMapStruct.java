package com.example.demo.mapstruct;

import com.example.demo.model.dto.OrderDto;
import com.example.demo.model.entity.OrderEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapStruct {
    OrderDto orderEntityToOrderDto(OrderEntity orderEntity);

    List<OrderDto> orderEntityListToOrderDtoList(List<OrderEntity> orderEntities);

    OrderEntity orderDtoToOrderEntity(OrderDto orderDto);
}
