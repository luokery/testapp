package com.example.demo.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.MappedSuperclass;
import lombok.EqualsAndHashCode;
import lombok.Data;

import java.sql.Timestamp;

/**
 * Base Entity Class
 */
@EqualsAndHashCode
@Data
@MappedSuperclass
public class BaseEntity {

    /**
     * Creation time
     */
    @Column(name = "create_time", columnDefinition = "创建时间", nullable = false)
    private Timestamp createTime;

    /**
     * Update time
     */
    @Column(name = "update_time", columnDefinition = "更新时间")
    private Timestamp updateTime;
    
    /**
     * Record status, such as: 'ACTIVE', 'DELETED'
     */
    @Column(name = "record_status", columnDefinition = "记录状态")
    private String recordStatus;
}
