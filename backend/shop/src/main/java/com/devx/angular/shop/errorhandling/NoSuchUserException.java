package com.devx.angular.shop.errorhandling;

public class NoSuchUserException extends RuntimeException {
	public NoSuchUserException(final String message) {
		super(message);
	}
}
