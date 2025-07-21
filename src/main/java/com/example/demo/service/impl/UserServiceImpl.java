package com.example.demo.service.impl;

import com.example.demo.model.vo.PageInfo;
import com.example.demo.dao.UserDao;
import com.example.demo.mapstruct.UserMapStruct;
import com.example.demo.model.dto.UserDto;
import com.example.demo.model.dto.PageInfoDto;
import com.example.demo.model.entity.UserEntity;
import com.example.demo.service.api.UserService;
import com.example.demo.model.vo.PageInfo;
import com.example.demo.service.api.BaseService;import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import java.util.concurrent.atomic.AtomicLong;

import java.util.stream.Collectors;

import org.apache.ibatis.session.RowBounds;
import java.util.*;

/**
 * This class provides the implementation for the UserService interface.
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService, BaseService<UserDto> {
    
    @Autowired
    private UserDao userDao;

    @Autowired    
    private UserMapStruct userMapStruct;

    private AtomicLong nextUserId = new AtomicLong(1003); // Start from 1003

    @Override
    public List<UserDto> getAll() {
        log.info("Starting to get all users");
        return userDao.getAll().stream()
                .map(userMapStruct::userEntityToUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto getById(String userId) {
        log.info("Retrieving user by ID: {}", userId);
        UserEntity userEntity = userDao.getById(userId);
        log.debug("Found user: {}", userEntity);
        return userMapStruct.userEntityToUserDto(userEntity);
    }

    @Override
    public PageInfo<UserDto> getAllUsersWithPagination(PageInfoDto<UserDto> pageInfoDto) {
        log.info("Starting to get all users with pagination - Page: {}, Size: {}, Query: {}", pageInfoDto.getPageNumber(), pageInfoDto.getPageSize(), pageInfoDto.getParamModel());
        int pageNumber = pageInfoDto.getPageNumber();
        int pageSize = pageInfoDto.getPageSize();
        UserDto queryDto = pageInfoDto.getParamModel();

        UserEntity queryEntity = queryDto != null ? userMapStruct.dtoToEntity(queryDto) : null;
        RowBounds rowBounds = new RowBounds((pageNumber - 1) * pageSize, pageSize);
        List<UserEntity> userEntities = userDao.getAllsWithPagination(queryEntity, rowBounds);
        List<UserDto> userDtos = userMapStruct.userEntityListToUserDtoList(userEntities);
        
        long totalElements = userDao.queryCount(queryEntity);
        
		// 计算出总页数
        long totalPages = (totalElements + pageSize - 1) / pageSize;
        
        PageInfo<UserDto> pageInfo = new PageInfo<UserDto>(pageNumber, pageSize, totalElements, pageSize, null, null);
        pageInfo.setPageNumber(pageNumber);
        pageInfo.setPageSize(pageSize);
        pageInfo.setTotalElements(totalElements); // Total count
        pageInfo.setTotalPages(totalPages);
        pageInfo.setParamModel(queryDto);
        pageInfo.setResultDatas(userDtos);
        return pageInfo;
    }
    
    @Override
    public void insert(UserDto userDto) {
    	log.info("Inserting new user: {}", userDto);
        UserEntity userEntity = userMapStruct.dtoToEntity(userDto);
        if (userEntity.getUserId() == null) {
            userEntity.setUserId(getNextUserId());
        }
        userDao.insert(userEntity);
        log.debug("Inserted user with ID: {}", userEntity.getUserId());
    }

    @Override
    public void update(UserDto userDto) {
        log.info("Updating user: {}", userDto);
        UserEntity userEntity = userMapStruct.dtoToEntity(userDto);
        userDao.update(userEntity);
    }

    @Override
    public void delete(String id) {
        log.info("Deleting user with ID: {}", id);
        userDao.delete(id);
    }

    private String getNextUserId() {
        return "USER_" + nextUserId.incrementAndGet();
    }
}
