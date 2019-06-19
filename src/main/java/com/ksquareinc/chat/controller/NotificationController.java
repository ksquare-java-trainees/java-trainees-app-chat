package com.ksquareinc.chat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ksquareinc.chat.model.Message;
import com.ksquareinc.chat.model.Notification;
import com.ksquareinc.chat.model.User;
import com.ksquareinc.chat.service.MessageService;

@RestController
@RequestMapping("/notify")
public class NotificationController {
	
	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;
	
	@Autowired
	private MessageService messageService;
	
	@PostMapping
	public void doNotify(@RequestBody Notification notification) {
		Message message = notification.toMessage(notification);
		for(User user:notification.getUsers()) {
			messageService.create(message);
			//simpMessagingTemplate.convertAndSendToUser("/queue/private", user.getUsername(), notification.toMessage(notification));
			//simpMessagingTemplate.convertAndSend("/topic/messages/" + user.getUsername(), message);
		}
	}
}