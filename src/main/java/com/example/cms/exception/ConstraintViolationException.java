package com.example.cms.exception;

import org.springframework.http.HttpStatus;

public class ConstraintViolationException extends RuntimeException {

	private String message ;

	public ConstraintViolationException(String message) {
		super();
	}
	
	public ConstraintViolationException(HttpStatus notFound) {
		
	}	
	
	public void setMessage(String message) {
		this.message=message;
	}

	public String getMessage() {
		return message;
	}
	
}
