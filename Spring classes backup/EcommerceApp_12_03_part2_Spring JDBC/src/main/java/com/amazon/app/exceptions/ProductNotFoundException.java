package com.amazon.app.exceptions;

public class ProductNotFoundException extends Exception {

	public ProductNotFoundException(String message) {
		super(message);// will call Exception constructor to forward the message
	}
}
