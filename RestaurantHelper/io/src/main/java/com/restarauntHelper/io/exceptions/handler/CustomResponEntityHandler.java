package com.restarauntHelper.io.exceptions.handler;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.restarauntHelper.io.exceptions.ExceptionResponse;
import com.restarauntHelper.io.exceptions.GarcomNotFoundException;
import com.restarauntHelper.io.exceptions.PedidoNotFoundException;

@RestController
@ControllerAdvice
public class CustomResponEntityHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ExceptionResponse> handleAllExceptions(Exception e, WebRequest request) {
		ExceptionResponse response = new ExceptionResponse(new Date(),e.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	
	}
	
	@ExceptionHandler(PedidoNotFoundException.class)
	public final ResponseEntity<ExceptionResponse> handleAllPeidodosNotFoundExceptions(Exception e, WebRequest request) {
		ExceptionResponse response = new ExceptionResponse(new Date(),e.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	
	}
	
	@ExceptionHandler(GarcomNotFoundException.class)
	public final ResponseEntity<ExceptionResponse> handleAllGarcomsNotFoundExceptions(Exception e, WebRequest request) {
		ExceptionResponse response = new ExceptionResponse(new Date(),e.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	
	}
	
	
}
