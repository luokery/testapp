package com.example.demo.model.vo;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class BaseVo {
    private Timestamp createTime;
    private Timestamp updateTime;
    private String recordStatus;
}