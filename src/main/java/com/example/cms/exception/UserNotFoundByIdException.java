package com.example.cms.exception;

import org.springframework.http.HttpStatus;

public class UserNotFoundByIdException extends RuntimeException {

	private String message ;

	public UserNotFoundByIdException(String message) {
		super();
		this.message=message;
	}
	
	public UserNotFoundByIdException(HttpStatus notFound) {
		
	}	
	
	public void setMessage(String message) {
		this.message=message;
	}

	public String getMessage() {
		return message;
	}
	
}
