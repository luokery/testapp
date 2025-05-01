package com.example.demo.dao;

import com.example.demo.model.entity.OrderEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderDao extends BaseDao<OrderEntity> {
    List<OrderEntity> getAllOrdersWithPagination(@Param("orderEntity") OrderEntity orderEntity,
                                                 @Param("offset") int offset,
                                                 @Param("pageSize") int pageSize);
}