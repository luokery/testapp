package com.example.demo.dao;

import org.apache.ibatis.annotations.Mapper;
import com.example.demo.model.entity.UserEntity;

@Mapper
public interface UserDao extends BaseDao<UserEntity> {

}
