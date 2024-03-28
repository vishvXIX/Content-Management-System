package com.example.cms.exception;

import org.springframework.http.HttpStatus;

public class TopicsNotSpecifiedException extends RuntimeException {

	private String message ;

	public TopicsNotSpecifiedException(String message) {
		super();
		this.message=message;
	}
	
	public TopicsNotSpecifiedException(HttpStatus notFound) {
		
	}	
	
	public void setMessage(String message) {
		this.message=message;
	}

	public String getMessage() {
		return message;
	}
	
}
