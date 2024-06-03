package com.customException;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class ErrorResponse {
	
	String message;
	
	LocalDateTime timeStamp;
	
	HttpStatus errorCode;
	

}
