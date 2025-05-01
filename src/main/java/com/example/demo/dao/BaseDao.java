package com.example.demo.dao;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BaseDao<T> {
    List<T> getAll();
    T getById(String id);
    void insert(T entity);
    void update(T entity);
    void delete(String id);
}
