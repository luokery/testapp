package com.example.demo.model.dto;

import lombok.Data;

/**
 * User data transfer object (DTO)
 * User DTO
 */
@Data
public class UserDto extends BaseDto{
    private Long userId;
    private String userName;
    private String password;

}