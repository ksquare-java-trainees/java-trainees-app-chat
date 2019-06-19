package com.ksquareinc.chat.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

public class PreAuthTokenHeaderFilter extends AbstractPreAuthenticatedProcessingFilter {

	private String userHeaderName;
	private String tokenHeaderName;

	public PreAuthTokenHeaderFilter(String userHeaderName, String tokenHeaderName) {
		this.userHeaderName  = userHeaderName;
		this.tokenHeaderName = tokenHeaderName;
	}

	@Override
	protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
		return request.getHeader(userHeaderName);
	}

	@Override
	protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {
		return request.getHeader(tokenHeaderName);
	}
}