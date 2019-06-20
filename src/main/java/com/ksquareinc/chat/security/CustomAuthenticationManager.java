package com.ksquareinc.chat.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
public class CustomAuthenticationManager implements AuthenticationManager{
	
	@Value("${ksquareChat.http.auth.SSOAuth}")
	private String SSOAuth;
	
	@Value("${ksquareChat.http.auth.SSOUser}")
	private String SSOUser;
	
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		final String urlAuth = SSOAuth;
		final String urlUser = SSOUser;
		
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new GrantedAuthorityImpl("USER"));
		
		String token = (String) authentication.getCredentials();
		String user  = (String) authentication.getPrincipal();
		
    	RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        HttpEntity<String> entity = new HttpEntity<String>("body", headers);

        try {
        	restTemplate.exchange(urlAuth, HttpMethod.GET, entity, String.class);
        } catch (HttpClientErrorException e) {
        	throw new BadCredentialsException("Forbbiden");
        }
        String[] userArray = {user};
        HttpEntity<String[]> postBody = new HttpEntity<String[]>(userArray, headers);
        
        try {
        	ResponseEntity<String> response = restTemplate.exchange(urlUser, HttpMethod.POST, postBody, String.class);
        	if(!response.getBody().equals("[]")) {
        		throw new BadCredentialsException("Forbbiden");
        	}
        	
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