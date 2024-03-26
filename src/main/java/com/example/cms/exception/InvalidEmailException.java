package com.example.cms.exception;

import org.springframework.http.HttpStatus;

public class InvalidEmailException extends RuntimeException {
	private String message ;

	public InvalidEmailException(String message) {
		super();
		this.message=message;
	}
	
	public InvalidEmailException(HttpStatus notFound) {
		
	}	
	
	public void setMessage(String message) {
		this.message=message;
	}

	public String getMessage() {
		return message;
	}
}
