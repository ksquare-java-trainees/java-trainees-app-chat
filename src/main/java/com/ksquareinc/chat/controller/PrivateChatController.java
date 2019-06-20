package com.ksquareinc.chat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.ksquareinc.chat.model.User;
import com.ksquareinc.chat.service.ConversationService;
import com.ksquareinc.chat.service.MessageService;
import com.ksquareinc.chat.service.UserService;
import com.ksquareinc.chat.model.Conversation;
import com.ksquareinc.chat.model.Message;

@Controller
public class PrivateChatController {
	
	@Autowired
    private SimpMessagingTemplate simpMessagingTemplate;
	
	@Autowired
	private ConversationService conversationService;
	@Autowired
	private MessageService messageService;
	@Autowired
	private UserService userService;
	
	@MessageMapping("/send/private")
	public void toUserMessage(SimpMessageHeaderAccessor sha, Message message) {	
		
		Conversation conversation = conversationService.findOne(message.getConversation().getId());
		User sender = userService.findByName(message.getSender().getUsername());
		List<User> users = conversation.getUsers();
		
		message.setConversation(conversation);
		message.setSender(sender);
		
		Message createMessage = messageService.create(message);
		
		for(User user : users) {
			this.simpMessagingTemplate.convertAndSendToUser(user.getUsername(), "/queue/private", createMessage);
		}
	}
}
