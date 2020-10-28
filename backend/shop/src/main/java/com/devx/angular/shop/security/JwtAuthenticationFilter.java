package com.devx.angular.shop.security;

import com.devx.angular.shop.services.DefaultJwtService;
import com.devx.angular.shop.services.UserService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private DefaultJwtService defaultJwtService;
	private UserService userService;

	public JwtAuthenticationFilter(final DefaultJwtService defaultJwtService, final UserService userService) {
		this.defaultJwtService = defaultJwtService;
		this.userService = userService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String header = request.getHeader("X-Auth-Token");
		if (header == null) {
			chain.doFilter(request, response);
			return;
		}
		login(header);
		chain.doFilter(request, response);
	}

	private void login(String header) {
		String subject = defaultJwtService.getSubject(header);
		if (userService.isUser(subject)) {
			var auth = new UsernamePasswordAuthenticationToken(subject, null, null);
			SecurityContextHolder.getContext().setAuthentication(auth);
		}
	}
}
