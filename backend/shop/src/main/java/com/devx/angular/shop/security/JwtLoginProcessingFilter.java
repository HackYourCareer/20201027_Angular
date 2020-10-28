package com.devx.angular.shop.security;

import com.devx.angular.shop.dto.UserDto;
import com.devx.angular.shop.services.DefaultJwtService;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class JwtLoginProcessingFilter extends AbstractAuthenticationProcessingFilter {

	private DefaultJwtService jwtService;

	protected JwtLoginProcessingFilter(AuthenticationManager authenticationManager, DefaultJwtService jwtService) {
		super(new AntPathRequestMatcher("/login", "POST"));
		setAuthenticationManager(authenticationManager);
		this.jwtService = jwtService;
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
											Authentication authentication) throws IOException, ServletException
	{
		String token = jwtService.generateJwt(((User) authentication.getPrincipal()).getUsername());
		response.addHeader("X-Auth-Token", token);
		response.addHeader("aabb", token);
//		response.getWriter().write("{\"token\":\""+token+"\"}");
		response.getWriter().write(token);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException {
		if (!request.getMethod().equals("POST")) {
			throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
		}

		UserDto userCredentials = getUserCredentials(request);
		String userName = userCredentials.getUserName();

		if (StringUtils.isBlank(userName) || StringUtils.isBlank(userCredentials.getPassword())) {
			throw new AuthenticationServiceException("Username or Password not provided");
		}
		return getAuthenticationManager()
				.authenticate(new UsernamePasswordAuthenticationToken(userName, userCredentials.getPassword(),
						new ArrayList<>()));
	}

	private UserDto getUserCredentials(HttpServletRequest request) throws IOException {
		try {
			return new ObjectMapper().readValue(request.getInputStream(), UserDto.class);
		} catch (JsonMappingException exception) {
			throw new AuthenticationServiceException("Username or Password not provided", exception);
		}
	}


}
