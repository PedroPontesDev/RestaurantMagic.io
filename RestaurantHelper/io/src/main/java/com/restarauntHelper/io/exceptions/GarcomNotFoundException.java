package com.restarauntHelper.io.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class GarcomNotFoundException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public GarcomNotFoundException(String message) {
		super(message);
	}
	
	

}
