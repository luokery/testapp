package com.example.demo.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * Order entity class
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "TBL_orders")
public class OrderEntity extends BaseEntity {
    /**
     * Order ID (for application logic and database primary key)
     */
    @Id
    @Column(name = "order_id", columnDefinition = "订单ID，主键")
    private Long orderId;

    /**
     * User ID (foreign key to TBL_users)
     */
    @Column(name = "user_id", columnDefinition = "用户ID，外键")
    private Long userId;

    /**
     * Order total price
     */
    @Column(name = "total_price", columnDefinition = "订单总价")
    private BigDecimal totalPrice;

    /**
     * Order status (e.g., "PENDING", "SHIPPED", "DELIVERED")
     */
    @Column(name = "order_status", columnDefinition = "订单状态")
    private String orderStatus;

}
