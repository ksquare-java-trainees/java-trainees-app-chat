package com.ksquareinc.chat.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ksquareinc.chat.error.ApiErrorResponse;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(value = {RuntimeException.class})
	protected ResponseEntity<ApiErrorResponse> handleConflict(RuntimeException ex, WebRequest request) {
		
		ApiErrorResponse apiErrorResponse = new ApiErrorResponse(HttpStatus.CONFLICT, ex.getLocalizedMessage(), ex.getClass().getName());
		return new ResponseEntity<ApiErrorResponse>(apiErrorResponse, HttpStatus.CONFLICT);
	}
}