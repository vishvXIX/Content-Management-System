package com.example.cms.exception;

import org.springframework.http.HttpStatus;

public class ContributionPanelNotFoundException extends RuntimeException {

	private String message ;

	public ContributionPanelNotFoundException(String message) {
		super();
		this.message=message;
	}
	
	public ContributionPanelNotFoundException(HttpStatus notFound) {
		
	}	
	
	public void setMessage(String message) {
		this.message=message;
	}

	public String getMessage() {
		return message;
	}
	
	
}
