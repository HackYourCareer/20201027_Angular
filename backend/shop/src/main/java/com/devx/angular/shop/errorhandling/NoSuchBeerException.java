package com.devx.angular.shop.errorhandling;

public class NoSuchBeerException extends RuntimeException {

	public NoSuchBeerException(final String message) {
		super(message);
	}
}
