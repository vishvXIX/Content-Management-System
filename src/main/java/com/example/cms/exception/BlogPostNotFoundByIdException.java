package com.example.cms.exception;

import org.springframework.http.HttpStatus;

public class BlogPostNotFoundByIdException extends RuntimeException {

	private String message ;

	public BlogPostNotFoundByIdException(String message) {
		super();
		this.message=message;
	}
	
	public BlogPostNotFoundByIdException(HttpStatus notFound) {
		
	}	
	
	public void setMessage(String message) {
		this.message=message;
	}

	public String getMessage() {
		return message;
	}
	
}
