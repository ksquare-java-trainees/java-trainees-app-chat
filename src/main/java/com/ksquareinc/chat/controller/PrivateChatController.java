package com.ksquareinc.chat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class PrivateChatController {

	@Autowired
    private SimpMessagingTemplate simpMessagingTemplate;
	
	// Message from server to a specific User
	@MessageMapping("/send/private")
	public Message toUserMessage(SimpMessageHeaderAccessor sha, @Payload Message message) {
		this.simpMessagingTemplate.convertAndSendToUser(sha.getUser().getName(), "/queue/private", message);
		return message;
	}
	
	// Messages between users using a personal subscription 
	@MessageMapping("/send/message")
    public Message privateMessage(@Payload Message message){
        this.simpMessagingTemplate.convertAndSend("/topic/"+message.getTo()  ,message);
        this.simpMessagingTemplate.convertAndSend("/topic/"+message.getFrom(),message);
        return message;
    }
	
}
