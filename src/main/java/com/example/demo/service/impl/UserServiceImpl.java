package com.example.demo.service.impl;

import com.example.demo.mapstruct.UserMapStruct;
import com.example.demo.model.dto.UserDto;
import com.example.demo.model.entity.UserEntity;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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