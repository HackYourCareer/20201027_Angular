package com.devx.angular.shop.security;

import com.devx.angular.shop.services.DefaultJwtService;
import com.devx.angular.shop.services.UserDetailsServiceImpl;
import com.devx.angular.shop.services.UserService;
import com.google.common.collect.ImmutableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private UserDetailsServiceImpl userDetailsService;
	private DefaultJwtService jwtService;
	private UserService userService;

	@Autowired
	public SecurityConfig(final UserDetailsServiceImpl userDetailsService, final DefaultJwtService jwtService,
						  final UserService userService) {
		this.userDetailsService = userDetailsService;
		this.jwtService = jwtService;
		this.userService = userService;
	}

	@Bean
	JwtLoginProcessingFilter jwtLoginProcessingFilter() throws Exception {
		return new JwtLoginProcessingFilter(authenticationManager(), jwtService);
	}

	@Bean
	JwtAuthenticationFilter jwtAuthenticationFilter() throws Exception {
		return new JwtAuthenticationFilter(jwtService, userService);
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(NoOpPasswordEncoder.getInstance());
	}

	@Override
	public void configure(WebSecurity web) {
		web.ignoring().antMatchers("/images/**", "/test/**", "/api-docs", "/swagger-ui", "/v2/api-docs",
				"/configuration/ui",
				"/swagger-resources/**",
				"/configuration/security",
				"/swagger-ui.html",
				"/webjars/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// @formatter:off
        http.csrf().disable().cors().and()
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
				.addFilterAfter(jwtLoginProcessingFilter(), UsernamePasswordAuthenticationFilter.class)
				.addFilterBefore(jwtAuthenticationFilter(), BasicAuthenticationFilter.class)
			.authorizeRequests()
        	.antMatchers(HttpMethod.POST,"/login", "/user").anonymous()
        	.antMatchers(HttpMethod.GET, "/user/**").anonymous()

        	.anyRequest()
				.authenticated()
        ;
     // @formatter:on
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedMethods("HEAD", "GET", "PUT", "POST", "DELETE", "PATCH", "OPTIONS").allowedOrigins("*");
			}
		};
	}
}
