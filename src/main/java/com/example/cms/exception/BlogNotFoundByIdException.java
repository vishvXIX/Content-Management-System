package com.example.cms.exception;

import org.springframework.http.HttpStatus;

public class BlogNotFoundByIdException extends RuntimeException {

	private String message ;

	public BlogNotFoundByIdException(String message) {
		super();
		this.message=message;
	}
	
	public BlogNotFoundByIdException(HttpStatus notFound) {
		
	}	
	
	public void setMessage(String message) {
		this.message=message;
	}

	public String getMessage() {
		return message;
	}
	
}
