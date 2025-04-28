package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import com.example.demo.model.entity.UserEntity;

@Mapper
public interface UserMapper {
	@Select("SELECT id, name FROM user")
	List<UserEntity> getAllUsers();
}