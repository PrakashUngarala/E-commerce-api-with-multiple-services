package com.nit.Advice;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	
	@ExceptionHandler(IOException.class)
	public ResponseEntity<String> handleIOException(IOException ex) {
		
	    return new ResponseEntity<>("IOException occured : "+ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<String> handleIOException1(ProductNotFoundException ex) {
		
	    return new ResponseEntity<>("ProductNotFoundException occured : "+ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	
	@ExceptionHandler(UserNameIsNullExp.class)
	public ResponseEntity<String> handleIOException1(UserNameIsNullExp ex) {
		
	    return new ResponseEntity<>("UserNameIsNullExp occured : "+ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
