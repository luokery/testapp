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
import org.slf4j.Logger;
import java.util.*;

/**
 * This class provides the implementation for the UserService interface.
 */
@Service
public class UserServiceImpl implements UserService, BaseService<UserDto> {
    
    @Slf4j
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
        UserEntity userEntity = userDao.getUserById(userId);
        log.debug("Found user: {}", userEntity);
        return userMapStruct.userEntityToUserDto(userEntity);
    }

    @Override
    public PageInfo<UserDto> getAllUsersWithPagination(PageInfoDto<UserDto> pageInfoDto) {
        log.info("Starting to get all users with pagination - Page: {}, Size: {}, Query: {}", pageInfoDto.getPageNumber(), pageInfoDto.getPageSize(), pageInfoDto.getQuery());
        int pageNumber = pageInfoDto.getPageNumber();
        int pageSize = pageInfoDto.getPageSize();
        UserDto queryDto = pageInfoDto.getQuery();

        UserEntity queryEntity = queryDto != null ? userMapStruct.userDtoToUserEntity(queryDto) : null;
        List<UserEntity> userEntities = userDao.getAllUsersWithPagination((pageNumber - 1) * pageSize, pageSize, queryEntity);
        List<UserDto> userDtos = userEntities.stream()
                .map(userMapStruct::userEntityToUserDto)
                .collect(Collectors.toList());
        PageInfo<UserDto> pageInfo = new PageInfo<>();
        pageInfo.setPageNumber(pageNumber);
        int totalElements = userDao.getAllUsersWithPagination(0, Integer.MAX_VALUE, queryEntity).size();
        pageInfo.setPageSize(pageSize);
        pageInfo.setTotalElements(userDao.getAll().size()); // Total count
        pageInfo.setContent(userDtos);
        return pageInfo;
    }
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
     @Override
    public void insert(UserDto userDto) {
        logger.info("Inserting new user: {}", userDto);
        UserEntity userEntity = userMapStruct.userDtoToUserEntity(userDto);
        if (userEntity.getUserId() == null) {
            userEntity.setUserId(getNextUserId());
        }
        userDao.insert(userEntity);
        log.debug("Inserted user with ID: {}", userEntity.getUserId());
    }

    @Override
    public void update(UserDto userDto) {
        log.info("Updating user: {}", userDto);
        UserEntity userEntity = userMapStruct.userDtoToUserEntity(userDto);
        userDao.updateUser(userEntity);
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
