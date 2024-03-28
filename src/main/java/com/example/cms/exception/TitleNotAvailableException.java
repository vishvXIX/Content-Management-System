package com.example.cms.exception;

import org.springframework.http.HttpStatus;

public class TitleNotAvailableException extends RuntimeException {

	private String message ;

	public TitleNotAvailableException(String message) {
		super();
		this.message=message;
	}
	
	public TitleNotAvailableException(HttpStatus notFound) {
		
	}	
	
	public void setMessage(String message) {
		this.message=message;
	}

	public String getMessage() {
		return message;
	}
	
}
