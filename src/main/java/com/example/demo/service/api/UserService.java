package com.example.demo.service.api;

import com.example.demo.model.dto.UserDto;
import com.example.demo.model.dto.PageInfoDto;
import com.example.demo.model.vo.PageInfo;

/**
 * UserService interface for managing user-related operations.
 */
public interface UserService extends BaseService<UserDto> {

    PageInfo<UserDto> getAllUsersWithPagination(PageInfoDto<UserDto> pageInfoDto);


}
