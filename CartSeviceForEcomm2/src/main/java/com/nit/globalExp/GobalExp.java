package com.nit.globalExp;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GobalExp {

	@ExceptionHandler(UserNameIsNull.class)
	public ResponseEntity<String> usernameIsNull(){
		
		return new ResponseEntity<String>("Username is null ",HttpStatus.BAD_GATEWAY);
	}
}
