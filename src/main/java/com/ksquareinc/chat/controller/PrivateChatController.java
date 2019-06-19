package com.ksquareinc.chat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.ksquareinc.chat.model.User;
import com.ksquareinc.chat.service.ConversationService;
import com.ksquareinc.chat.model.Message;

@Controller
public class PrivateChatController {

	@Autowired
    private SimpMessagingTemplate simpMessagingTemplate;
	
	@Autowired
	private ConversationService conversationService;
	
	// Message from server to a specific User
	@MessageMapping("/send/private")
	public Message toUserMessage(SimpMessageHeaderAccessor sha, @Payload Message message) {
		List<User> users = conversationService.findOne(message.getConversation().getId()).getUsers();
		for(User user : users) {
			this.simpMessagingTemplate.convertAndSendToUser(user.getUsername(), "/queue/private", message);
		}
		return message;
	}	
}
