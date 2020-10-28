package com.devx.angular.shop.controlers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.devx.angular.shop.dto.UserDto;
import com.devx.angular.shop.dto.UsernameValidationResult;
import com.devx.angular.shop.services.UserService;

import io.swagger.annotations.ApiOperation;


@RestController
public class UserController
{

	private UserService userService;

	@Autowired
	public UserController(final UserService userService)
	{
		this.userService = userService;
	}

	@PostMapping(path = "user", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation("Create new user")
	public void createUser(@Valid @RequestBody UserDto userDto)
	{
		userService.createNewUser(userDto);
	}

	@GetMapping(path = "user/{userName}")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation("Create new user")
	public UsernameValidationResult validateUserName(@PathVariable("userName") String username)
	{
			return userService.validateUsername(username);
	}


}
