package com.example.demo.mapstruct;

import com.example.demo.model.dto.UserDto;
import com.example.demo.model.vo.PageInfo;
import com.example.demo.model.entity.UserEntity;
import com.example.demo.model.vo.user.UserVo;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface UserMapStruct {
    @Mappings({
            @Mapping(source = "content", target = "content")
    })
    abstract PageInfo<UserVo> userDtoPageInfoToUserVoPageInfo(PageInfo<UserDto> userDtoPageInfo);
    @InheritInverseConfiguration
    abstract PageInfo<UserDto> userVoPageInfoToUserDtoPageInfo(PageInfo<UserVo> userVoPageInfo);
    abstract UserDto userVoToUserDto(UserVo userVo);
    @InheritInverseConfiguration
    abstract UserVo userDtoToUserVo(UserDto userDto);
    public abstract UserVo userDtoToUserVo(UserDto userDto);

    public abstract List<UserVo> userDtoListToUserVoList(List<UserDto> userDtos);

    public abstract UserDto userEntityToUserDto(UserEntity userEntity);

    public abstract List<UserDto> userEntityListToUserDtoList(List<UserEntity> userEntities);
}