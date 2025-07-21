package com.example.demo.mapstruct;

import com.example.demo.model.dto.OrderDto;
import com.example.demo.model.entity.OrderEntity;
import com.example.demo.model.vo.order.OrderVo;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface OrderMapStruct {
    OrderDto orderVoToOrderDto(OrderVo orderVo);

    List<OrderDto> orderVoListToOrderDtoList(List<OrderVo> orderVos);

    OrderVo orderDtoToOrderVo(OrderDto orderDto);

    List<OrderVo> orderDtoListToOrderVoList(List<OrderDto> orderDtos);

    List<OrderDto> orderEntityListToOrderDtoList(List<OrderEntity> orderEntities);

    OrderEntity orderDtoToOrderEntity(OrderDto orderDto);
    OrderDto orderEntityToOrderDto(OrderEntity orderEntity);
}


