package com.ksquareinc.chat.config;

import java.security.Principal;
import java.util.Map;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import com.ksquareinc.chat.model.StompPrincipal;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomHandshakeHandler extends DefaultHandshakeHandler {
	
    @Override
    protected Principal determineUser(ServerHttpRequest request,
                                      WebSocketHandler wsHandler,
                                      Map<String, Object> attributes) {
    	
    	//HttpServletRequest req = ((ServletServerHttpRequest) request).getServletRequest();
    	log.info("A DEBUG Message");
    	return new StompPrincipal("ALV"	);
    }
}