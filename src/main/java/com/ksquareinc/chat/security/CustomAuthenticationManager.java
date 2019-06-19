package com.ksquareinc.chat.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

public class CustomAuthenticationManager implements AuthenticationManager{
	
	private final String url = "http://localhost:8888/ksquare-sso/api/users/auth";
	
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new GrantedAuthorityImpl("USER"));
		
		String token = (String) authentication.getCredentials();
		String user  = (String) authentication.getPrincipal();
		
    	RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        HttpEntity<String> entity = new HttpEntity<String>("body", headers);

        try {
        	restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        } catch (HttpClientErrorException e) {
        	throw new BadCredentialsException("Forbbiden");
        }
        Authentication auth = new UsernamePasswordAuthenticationToken(user, token, authorities);
        
        return auth;
	}
	
	class GrantedAuthorityImpl implements GrantedAuthority{

		private String auth;
		
		public GrantedAuthorityImpl(String auth) {
			this.auth = auth;
		}
		
		@Override
		public String getAuthority() {
			return auth;
		}
		
	}

}
