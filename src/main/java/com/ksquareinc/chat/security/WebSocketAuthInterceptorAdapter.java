package com.ksquareinc.chat.security;

import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.ksquareinc.chat.model.User;
import com.ksquareinc.chat.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;

@Component
public class WebSocketAuthInterceptorAdapter implements ChannelInterceptor {
	
	@Value("${ksquareChat.http.auth.userName}")
    private String userHeaderName;
    
    @Value("${ksquareChat.http.auth.tokenName}")
    private String tokenHeaderName;
	
    @Autowired
    private CustomAuthenticationManager customAuthenticationManager;
    @Autowired
    private UserService userService;

	@Override
	public Message<?> preSend(final Message<?> message, final MessageChannel channel) throws AuthenticationException {
		
	    final StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
	
	    if (StompCommand.CONNECT.equals(accessor.getCommand())) {
	    	authenticateUser(accessor);
	    	setConnect(accessor.getUser().getName(), true);
	    }else if (StompCommand.DISCONNECT.equals(accessor.getCommand())) {
	    	setConnect(accessor.getUser().getName(), false);
	    }
	    
	    return message;
	}
	
	private void authenticateUser(StompHeaderAccessor accessor) {
		Authentication authenticatedUser = null;
    	
        String userName = accessor.getFirstNativeHeader(userHeaderName);
        String userToken  = accessor.getFirstNativeHeader(tokenHeaderName);
        
        authenticatedUser = customAuthenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, userToken));        
        accessor.setUser(authenticatedUser);
        isUser(userName);
	}
	
	private void isUser(String userName) {
		User user = userService.findByName(userName);
		//IF USER IS NOT IN OUR DATABASE WE NEED TO CREATE IT
		if(user == null) {
			user = new User(userName, true);
			userService.create(user);
		} 
	}
	
	private void setConnect(String userName, boolean isConnect) {
    	User user = userService.findByName(userName);
    	user.setActive(isConnect);
    	userService.update(user);
	}
}