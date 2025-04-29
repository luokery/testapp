package com.example.demo.model.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class BaseDto {
    private Timestamp createTime;
    private Timestamp updateTime;
}