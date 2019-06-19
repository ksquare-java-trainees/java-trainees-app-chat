package com.ksquareinc.chat.security;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ksquareinc.chat.error.ApiErrorResponse;

@Component
public class HttpAuthenticationEntryPoint implements AuthenticationEntryPoint {
	
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException authException) throws IOException {
        
    	response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
     
        ObjectMapper mapper = new ObjectMapper(); 
        ApiErrorResponse error = new ApiErrorResponse(HttpStatus.UNAUTHORIZED, "Unauthorized", authException);
        mapper.writeValue(response.getOutputStream(), error);
    }
}