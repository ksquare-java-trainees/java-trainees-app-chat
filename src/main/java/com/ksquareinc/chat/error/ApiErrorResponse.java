package com.ksquareinc.chat.error;

import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
public class ApiErrorResponse {

	private HttpStatus status;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private LocalDateTime timestamp;
	private String message;
	private String detail;
	//private List<ApiSubError> subErrors;
	
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

	public ApiErrorResponse(HttpStatus status, String message, Throwable ex) {
		this();
		this.status = status;
		this.message = message;
		this.detail = ex.getLocalizedMessage();
	}
	
}
