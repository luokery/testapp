package com.example.demo.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import java.util.List;

@Data
@AllArgsConstructor

public class PageInfo<T> {

    @Positive(message = "pageNumber must be greater than 0")
    private int pageNumber;
    @Positive(message = "pageSize must be greater than 0")
    @Max(value = 100, message = "pageSize cannot be greater than 100")
    private int pageSize;
    @Min(value = 0, message = "totalElements cannot be less than 0")
    private long totalElements;
    @Min(value = 0, message = "totalPages cannot be less than 0")
    private int totalPages;
    private List<T> content;

}
