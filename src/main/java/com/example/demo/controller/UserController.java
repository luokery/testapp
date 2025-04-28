package com.example.demo.controller;

import com.example.demo.model.dto.UserDto;
import com.example.demo.model.vo.user.UserVo;
import com.example.demo.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import com.example.demo.mapstruct.UserMapStruct;

@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	private UserMapStruct userMapStruct;
	@Autowired
	private UserService userService;

	@GetMapping()
	public List<UserVo> getAllUsers() {
		List<UserDto> userDtos = userService.getAllUsers();
		return userMapStruct.userDtoListToUserVoList(userDtos);
	}
}