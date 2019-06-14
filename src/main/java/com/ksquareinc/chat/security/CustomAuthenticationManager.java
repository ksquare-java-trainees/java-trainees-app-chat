package com.ksquareinc.chat.security;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

public class CustomAuthenticationManager implements AuthenticationManager{
	
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String principal = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1NjA0NTgxMzMsInVzZXJfbmFtZSI6ImNybWFkbWluIiwiYXV0aG9yaXRpZXMiOlsiUk9MRV9VU0VSIiwiUk9MRV9BRE1JTiJdLCJqdGkiOiJlOTVmYWUyNS00NjgyLTQ1ZmMtYTU1ZS0zNzIyYzQ5OTQzMTEiLCJjbGllbnRfaWQiOiJjaGF0SWQiLCJzY29wZSI6WyJyZWFkIiwid3JpdGUiLCJ0cnVzdCJdfQ.vdZTPHqTtwgBL0GFF-jXGazNIye7t2DF9pS3UHCwDrw";//(String) authentication.getPrincipal(); 
    	
    	final String url = "http://192.168.240.253:8080/ksquare-sso/api/users/auth";
        
    	RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(principal);
        HttpEntity<String> entity = new HttpEntity<String>("body", headers);

        try {
        	restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        } catch (HttpClientErrorException e) {
        	throw new BadCredentialsException("Token no valido");
        }
        
        authentication.setAuthenticated(true);
        return authentication;
	}

}
