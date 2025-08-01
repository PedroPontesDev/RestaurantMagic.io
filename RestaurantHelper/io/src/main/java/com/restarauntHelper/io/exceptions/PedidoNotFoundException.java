package com.restarauntHelper.io.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class PedidoNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public PedidoNotFoundException(String message) {
		super(message);
	}

}
