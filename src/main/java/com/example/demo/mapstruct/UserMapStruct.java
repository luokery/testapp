package com.example.demo.mapstruct;

import com.example.demo.model.dto.UserDto;
import com.example.demo.model.entity.UserEntity;
import com.example.demo.model.vo.user.UserVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public abstract class UserMapStruct {
    public static final UserMapStruct INSTANCE = Mappers.getMapper(UserMapStruct.class);

    public abstract UserVo userDtoToUserVo(UserDto userDto);

    public abstract List<UserVo> userDtoListToUserVoList(List<UserDto> userDtos);

    public abstract UserDto userEntityToUserDto(UserEntity userEntity);

    public abstract List<UserDto> userEntityListToUserDtoList(List<UserEntity> userEntities);
}