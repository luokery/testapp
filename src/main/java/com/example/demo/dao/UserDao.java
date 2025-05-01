package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.session.RowBounds;
import com.example.demo.model.entity.UserEntity;

@Mapper
public interface UserDao extends BaseDao<UserEntity> {

    List<UserEntity> getAllUsersWithPagination(@Param("user") UserEntity user, RowBounds rowBounds);

    @Select("SELECT COUNT(*) FROM t_user")
    long countAll();

}
