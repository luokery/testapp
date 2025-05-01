package com.example.demo.model.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class UserDto extends BaseDto{
    @NotNull(message = "userId can not be null")
    @Size(max = 50, message = "userId max length is 50")
    private String userId;

    @NotNull(message = "userName can not be null")
    @Size(max = 50, message = "userName max length is 50")
    private String userName;
    @NotNull(message = "password can not be null")
    @Size(max = 50, message = "password max length is 50")
    private String password;

}