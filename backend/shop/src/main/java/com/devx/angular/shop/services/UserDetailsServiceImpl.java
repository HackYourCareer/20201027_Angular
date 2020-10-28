package com.devx.angular.shop.services;

import com.devx.angular.shop.dto.UserDto;
import com.devx.angular.shop.errorhandling.NoSuchUserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private UserService userService;

	@Autowired
	public UserDetailsServiceImpl(final UserService userService) {
		this.userService = userService;
	}

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		UserDto user = userService.getUser(userName).orElseThrow(() -> new NoSuchUserException("Unknown user"));
		return new User(user.getUserName(), user.getPassword(), List.of(new SimpleGrantedAuthority("USER_ROLE")));
	}
}
