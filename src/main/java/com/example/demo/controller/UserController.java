package com.example.demo.controller;

import com.example.demo.model.dto.UserDto;
import com.example.demo.model.dto.PageInfoDto;
import com.example.demo.model.vo.PageInfo;
import com.example.demo.model.vo.user.UserVo;
import com.example.demo.service.api.UserService;
import jakarta.validation.Valid;import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
InfoDto<UserVo> pageInfoDto, Bindi	public ResponseEntity<?> getAllUsersWithPagination(@Validated @ModelAttribute PagengResult result) {
		if (result.hasErrors()) {
			return handleValidationExceptions(new MethodArgumentNotValidException(null, result));
		}
		UserVo userVo = pageInfoDto.getQuery();
		int pageNumber=pageInfoDto.getPageNumber();
		int pageSize=pageInfoDto.getPageSize();
		log.info("Start get all users with pagination, pageNumber: {}, pageSize: {}, query: {}", pageNumber, pageSize,pageInfoDto.getQuery());
		PageInfo<UserDto> userDtoPageInfo = userService.getAllUsersWithPagination(pageInfoDto);
		return userMapStruct.userDtoPageInfoToUserVoPageInfo(userDtoPageInfo);
	}


	@GetMapping("/{userId}")
	public ResponseEntity<UserVo> getUserById(@PathVariable String userId) {
		log.info("Start get user by id, userId: {}", userId);
		UserDto userDto = userService.getById(userId);
		if (userDto == null) {
		    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(userMapStruct.userDtoToUserVo(userDto), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Void> insertUser(@Validated @RequestBody UserDto userDto,BindingResult result) {
		log.info("Start insert user, userDto: {}", userDto);
		userService.insert(userDto);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<Void> updateUser(@Validated @RequestBody UserDto userDto,BindingResult result) {
		log.info("Start update user, userDto: {}", userDto);
		userService.update(userDto);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@DeleteMapping("/{userId}")
	public ResponseEntity<Void> deleteUser(@PathVariable String userId) {
		log.info("Start delete user, userId: {}", userId);
		userService.delete(userId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}