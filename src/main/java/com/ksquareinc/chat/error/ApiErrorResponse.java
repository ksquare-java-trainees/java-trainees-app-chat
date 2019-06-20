package com.ksquareinc.chat.error;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class ApiErrorResponse {

	private HttpStatus status;
	
	private String message;
	
	private List<String> errors;
	
	//Remove this one.
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private LocalDateTime timestamp;
	
	//Remove this one.
	private String detail;
	
	//Remove this one.
	private ApiErrorResponse() {
		timestamp = LocalDateTime.now();
	}

	public ApiErrorResponse(HttpStatus status) {
		this();
		this.status = status;
	}

	public ApiErrorResponse(HttpStatus status, Throwable ex) {
		this();
		this.status = status;
		this.message = "Unexpected error";
		this.detail = ex.getLocalizedMessage();
	}

	//Fix this one with HttpAuthenticationEntryPoint
	/*public ApiErrorResponse(HttpStatus status, String message, Throwable ex) {
		this();
		this.status = status;
		this.message = message;
		this.detail = ex.getLocalizedMessage();
	}*/
	
	public ApiErrorResponse(HttpStatus status, String message, String error) {
		super();
		this.status = status;
		this.message = message;
		this.errors = Arrays.asList(error);
	}
	
	public ApiErrorResponse(HttpStatus status, String message, List<String> errors) {
		super();
		this.status = status;
		this.message = message;
		this.errors = errors;
	}
	
	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}
}