package com.example.demo.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

@Mapper
public interface BaseDao<T> {
    List<T> getAll();
    T getById(String id);
    void insert(T entity);
    void update(T entity);
    void delete(String id);
    
	long queryCount(@Param("entity") T entity);
	
	List<T> getAllsWithPagination(@Param("entity") T entity, RowBounds rowBounds);
}
