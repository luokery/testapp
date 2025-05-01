package com.example.demo.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderDto extends BaseDto {
    @NotBlank(message = "orderId cannot be blank")
    @Size(max = 50, message = "orderId must be less than or equal to 50 characters")
    private String orderId;
    @NotBlank(message = "userId cannot be blank")
    @Size(max = 50, message = "userId must be less than or equal to 50 characters")
    private String userId;
    @NotNull(message = "totalPrice cannot be null")
    private BigDecimal totalPrice;
    @NotBlank(message = "orderStatus cannot be blank")
    @Size(max = 50, message = "orderStatus must be less than or equal to 50 characters")
    private String orderStatus;
}
