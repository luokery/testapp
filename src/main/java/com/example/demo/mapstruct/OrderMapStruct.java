package com.example.demo.mapstruct;

import com.example.demo.model.dto.OrderDto;
import com.example.demo.model.entity.OrderEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface OrderMapStruct {
    OrderMapStruct INSTANCE = Mappers.getMapper(OrderMapStruct.class);
    OrderDto orderEntityToOrderDto(OrderEntity orderEntity);

    List<OrderDto> orderEntityListToOrderDtoList(List<OrderEntity> orderEntities);

    OrderEntity orderDtoToOrderEntity(OrderDto orderDto);
}
