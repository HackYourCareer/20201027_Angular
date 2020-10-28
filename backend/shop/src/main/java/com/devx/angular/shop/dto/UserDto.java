package com.devx.angular.shop.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Data
public class UserDto {
	@NotNull
	@Length(min = 1)
	private String userName;
	@NotNull
	@Length(min = 1)
	private String password;
}
