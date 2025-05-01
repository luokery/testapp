package com.example.demo.model.vo.user;

import com.example.demo.model.vo.BaseVo;
import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * User VO
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserVo extends BaseVo {
    private String userId;
    private String userName;
}
