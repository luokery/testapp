package com.example.demo.service.impl;

import com.example.demo.mapstruct.UserMapStruct;
import com.example.demo.model.dto.UserDto;
import com.example.demo.model.entity.UserEntity;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.concurrent.atomic.AtomicLong;

import java.util.*;

/**
 * This class provides the implementation for the UserService interface.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    
    @Autowired    
    private UserMapStruct userMapStruct;


    private AtomicLong nextUserId = new AtomicLong(1003); // Start from 1003

    public UserEntity createUser(String userName, String password) {
        UserEntity user = new UserEntity();
        user.setUserId(nextUserId.getAndIncrement()); // Get the next available ID and increment the counter
        user.setUserName(userName);
        user.setPassword(password);
        // ... set other properties like createTime, updateTime ...
        // ... save the user to the database ...
        return user;
    }

    /**
     * Retrieves a list of all users from the database.
     *
     * @return A list of UserDto objects representing all users.
     */
    @Override
    public List<UserDto> getAllUsers() {
        List<UserEntity> userEntities = userMapper.getAllUsers();        
        return userMapStruct.userEntityListToUserDtoList(userEntities);
    }
}