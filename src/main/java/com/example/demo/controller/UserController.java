package com.example.demo.controller;

import com.example.demo.model.dto.UserDto;
import com.example.demo.model.dto.UserDto;
import com.example.demo.model.dto.PageInfoDto;
import com.example.demo.model.vo.PageInfo;
import com.example.demo.model.vo.user.UserVo;
import com.example.demo.model.dto.UserDto;
import com.example.demo.service.api.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import com.example.demo.mapstruct.UserMapStruct;


@Validated
@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	private UserMapStruct userMapStruct;
	@Autowired
	private UserService userService;

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getFieldErrors().forEach(error -> {
			errors.put(error.getField(), error.getDefaultMessage());
		});
		return ResponseEntity.badRequest().body(errors);
	}


	@GetMapping()
	public List<UserVo> getAllUsers() {
        log.info("Start get all users");
		List<UserDto> userDtos = userService.getAllUsers();
		return userMapStruct.userDtoListToUserVoList(userDtos);
	}

	@GetMapping("/page")
	public PageInfo<UserVo> getAllUsersWithPagination(@Validated @ModelAttribute PageInfo<UserVo> pageInfo, BindingResult result, @Validated UserVo userVo, BindingResult queryResult) {
		if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            result.getFieldErrors().forEach(error -> {
                errors.put(error.getField(), error.getDefaultMessage());
            });
			log.error("validation error :{}",errors);
            return null;
        }
		if (queryResult.hasErrors()) {
			Map<String, String> errors = new HashMap<>();
			result.getFieldErrors().forEach(error -> {
				errors.put(error.getField(), error.getDefaultMessage());
			});
			log.error("validation error :{}",errors);
			return null;
		}
		PageInfoDto<UserDto> pageInfoDto = new PageInfoDto<>();
        pageInfoDto.setPageNumber(pageInfo.getPageNumber());
        pageInfoDto.setPageSize(pageInfo.getPageSize());
		pageInfoDto.setQuery(userMapStruct.userVoToUserDto(userVo));
		if (result.hasErrors()) {
        log.info("Start get all users with pagination, pageInfoDto: {}", pageInfoDto);
        PageInfo<UserDto> userDtoPageInfo = userService.getAllUsersWithPagination(pageInfoDto);
        return userMapStruct.userDtoPageInfoToUserVoPageInfo(userDtoPageInfo);
	}


	@GetMapping("/{userId}")
	public UserVo getUserById(@PathVariable String userId) {
		log.info("Start get user by id, userId: {}", userId);
		UserDto userDto = userService.getById(userId);
		if (userDto == null) {
		    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(userMapStruct.userDtoToUserVo(userDto), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Void> insertUser(@Validated @RequestBody UserVo userVo, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (result.hasErrors()) {
        userService.insert(userMapStruct.userVoToUserDto(userVo));
        return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<?> updateUser(@Validated @RequestBody UserVo userVo, BindingResult result) {
        if (result.hasErrors()) {
           return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        log.info("Start update user, userVo: {}", userVo);
        userService.update(userMapStruct.userVoToUserDto(userVo));
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@DeleteMapping("/{userId}")
	public ResponseEntity<Void> deleteUser(@PathVariable String userId) {
		log.info("Start delete user, userId: {}", userId);
		userService.delete(userId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}