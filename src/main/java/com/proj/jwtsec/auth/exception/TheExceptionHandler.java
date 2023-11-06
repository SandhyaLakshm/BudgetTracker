package com.proj.jwtsec.auth.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class TheExceptionHandler {
	
	@ExceptionHandler
	public ResponseEntity<ErrorResponse> negMoney(NegMoneyException exc){
		ErrorResponse err=new ErrorResponse(exc.getMessage(),HttpStatus.BAD_REQUEST.value());
		return new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorResponse> duplicateEmail(DuplicateEmailException exc){
		ErrorResponse err=new ErrorResponse(exc.getMessage(),HttpStatus.BAD_REQUEST.value());
		return new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorResponse> generalException(Exception exc){
		ErrorResponse err=new ErrorResponse(exc.getMessage(),HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<>(err,HttpStatus.NOT_FOUND);
	}

}
