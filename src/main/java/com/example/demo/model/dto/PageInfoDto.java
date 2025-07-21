package com.example.demo.model.dto;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageInfoDto<T> {
    @Min(value = 1, message = "pageNumber must be greater than or equal to 1")
    private int pageNumber;
    @Min(value = 1, message = "pageSize must be greater than or equal to 1")
    @Max(value = 100, message = "pageSize cannot be greater than 100")    
    private int pageSize;
    
    @Valid
    private T paramModel;
    
    @Valid
    private List<T> resultDatas = null;
    
}