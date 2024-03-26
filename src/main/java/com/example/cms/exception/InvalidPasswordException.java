package com.example.cms.exception;

import org.springframework.http.HttpStatus;

public class InvalidPasswordException extends RuntimeException {
	private String message ;

	public InvalidPasswordException(String message) {
		super();
		this.message=message;
	}
	
	public InvalidPasswordException(HttpStatus notFound) {
		
	}	
	
	public void setMessage(String message) {
		this.message=message;
	}

	public String getMessage() {
		return message;
	}
}