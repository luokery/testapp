package com.example.demo.service.api;

import com.example.demo.model.dto.BaseDto;
import java.util.List;

public interface BaseService<T extends BaseDto> {
    void insert(T dto);
    void delete(String id);
    void update(T dto);
    List<T> getAll();
    T getById(String id);
}