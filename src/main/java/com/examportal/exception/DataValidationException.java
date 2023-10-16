package com.examportal.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)

public class DataValidationException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	private String message;

	
	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}

	

	public DataValidationException(String message) {
		super();
		this.message = message;
	}


	public DataValidationException() {

	}
	

}
