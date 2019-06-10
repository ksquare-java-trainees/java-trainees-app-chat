package com.ksquareinc.chat.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import com.ksquareinc.chat.model.WebSocketChatMessage;

@Controller
public class MessageController {

//	@MessageMapping("/chat")
//	@SendTo("/topic/messages")
//	public OutputMessage send(Message message) throws Exception {
//	    String time = new SimpleDateFormat("HH:mm").format(new Date());
//	    return new OutputMessage(message.getFrom(), message.getMessage(), time);
//	}
	@MessageMapping("/chat.sendMessage")
	@SendTo("/topic/javainuse")
	public WebSocketChatMessage sendMessage(@Payload WebSocketChatMessage webSocketChatMessage) {
		return webSocketChatMessage;
	}
	
	@MessageMapping("/chat.newUser")
	@SendTo("/topic/javainuse")
	public WebSocketChatMessage newUser(@Payload WebSocketChatMessage webSocketChatMessage,
			SimpMessageHeaderAccessor headerAccessor) {
		headerAccessor.getSessionAttributes().put("username", webSocketChatMessage.getSender());
		return webSocketChatMessage;
	}
	
}
