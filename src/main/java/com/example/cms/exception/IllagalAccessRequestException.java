package com.example.cms.exception;

import org.springframework.http.HttpStatus;

public class IllagalAccessRequestException extends RuntimeException {

	private String message ;

	public IllagalAccessRequestException(String message) {
		super();
		this.message=message;
	}
	
	public IllagalAccessRequestException(HttpStatus notFound) {
		
	}	
	
	public void setMessage(String message) {
		this.message=message;
	}

	public String getMessage() {
		return message;
	}
	
}
