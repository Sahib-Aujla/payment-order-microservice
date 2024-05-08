package com.payments.paymentService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


public class RestResponseEntityExceptionHandler extends  ResponseEntityExceptionHandler{
	@ExceptionHandler(CustomException.class)
	public ResponseEntity<ErrorResponse> handleCustomException(CustomException exception) {
		return new ResponseEntity<>(ErrorResponse
				.builder(exception, HttpStatus.valueOf(exception.getStatus()), exception.getMessage()).build(),
				HttpStatus.valueOf(exception.getStatus()));
	}
}
