package com.example.demo.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * User entity class
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "TBL_users")
public class UserEntity extends BaseEntity {

    /**
     * Primary key, not auto-incremented, for application logic usage
     */
    @Id
    @Column(name = "user_id", columnDefinition = "用户id，主键")
    private Long userId;

    /**
     * User name
     */
    @Column(name = "user_name", columnDefinition = "用户名")
    private String userName;

    /**
     * password
     */
    @Column(name = "password", columnDefinition = "密码")
    private String password;

}
