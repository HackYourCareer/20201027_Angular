package com.devx.angular.shop.services;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import com.devx.angular.shop.dto.UserDto;
import com.devx.angular.shop.dto.UsernameValidationResult;
import com.devx.angular.shop.errorhandling.NoSuchUserException;


@Service
public class UserService
{
	private Map<String, UserDto> users = new ConcurrentHashMap<>();

	public UserService()
	{
		createAdmin();
	}

	public void createNewUser(final UserDto userDto)
	{
		if (users.containsKey(userDto.getUserName()))
		{
			throw new NoSuchUserException("user already exist");
		}
		var user = new UserDto();
		user.setUserName(userDto.getUserName());
		user.setPassword(userDto.getPassword());
		users.put(user.getUserName(), user);
	}

	public UsernameValidationResult validateUsername(String username)
	{
		var result = new UsernameValidationResult();
		if (users.containsKey(username))
		{
			result.setResult(false);
			result.setMessage("Given user already exist");
		}
		else
		{
			result.setResult(true);
		}
		return result;
	}

	public boolean userExist(final UserDto userDto)
	{
		if (!users.containsKey(userDto.getUserName()))
		{
			throw new NoSuchUserException("User not exist");
		}
		if (!users.get(userDto.getUserName()).getPassword().equals(userDto.getPassword()))
		{
			throw new NoSuchUserException("User not exist");
		}
		return true;
	}

	public Optional<UserDto> getUser(final String userName)
	{
		return Optional.ofNullable(users.get(userName));
	}

	public boolean isUser(final String subject)
	{
		return users.containsKey(subject);
	}

	public void clear()
	{
		users.clear();
		createAdmin();
	}

	private void createAdmin()
	{
		var admin = new UserDto();
		admin.setUserName("admin");
		admin.setPassword("nimda");
		users.put("admin", admin);
	}
}
