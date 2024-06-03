package com.customException;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice // all controller access this Exception Advicer
public class GlobalExceptionAdvicer {

	@ExceptionHandler(GlobalExceptionHandler.class)
	public ResponseEntity<Object> HandlerCartItemException(GlobalExceptionHandler ex) {
		ErrorResponse response = new ErrorResponse();
		response.setMessage(ex.getMessage());
		response.setTimeStamp(LocalDateTime.now());
		response.setErrorCode(HttpStatus.NOT_FOUND);
		return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
	}

	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<Object>ResourceNotFoundException(ResourceNotFoundException ex) {
		ErrorResponse response = new ErrorResponse();
		response.setMessage(ex.getMessage());
		response.setTimeStamp(LocalDateTime.now());
		response.setErrorCode(HttpStatus.NOT_FOUND);
		return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
	}

}
