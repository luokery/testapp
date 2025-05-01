package com.example.demo.mapstruct;

import com.example.demo.model.dto.OrderDto;
import com.example.demo.model.dto.PageInfoDto;
import com.example.demo.model.entity.OrderEntity;
import com.example.demo.model.vo.PageInfo;
import com.example.demo.model.vo.OrderVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapStruct {
    OrderDto orderVoToOrderDto(OrderVo orderVo);

    List<OrderDto> orderVoListToOrderDtoList(List<OrderVo> orderVos);

    OrderVo orderDtoToOrderVo(OrderDto orderDto);

    List<OrderVo> orderDtoListToOrderVoList(List<OrderDto> orderDtos);

    List<OrderDto> orderEntityListToOrderDtoList(List<OrderEntity> orderEntities);

    OrderEntity orderDtoToOrderEntity(OrderDto orderDto);
}


