package com.devx.angular.shop.errorhandling;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestControllersAdvice extends ResponseEntityExceptionHandler {

	@ExceptionHandler({NoSuchBeerException.class})
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public HttpEntity<String> notFoundException(final Exception exception, final WebRequest request)
	{
		return new HttpEntity<>(exception.getMessage());
	}

	@ExceptionHandler({NoSuchUserException.class})
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public HttpEntity<String> userNotFoundException(final Exception exception, final WebRequest request)
	{
		return new HttpEntity<>(exception.getMessage());
	}
}
