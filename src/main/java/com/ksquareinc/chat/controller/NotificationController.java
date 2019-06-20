package com.ksquareinc.chat.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ksquareinc.chat.model.Notification;
import com.ksquareinc.chat.model.User;

@RestController
@RequestMapping("/notify")
public class NotificationController {
	
	private final Logger logger = Logger.getLogger(NotificationController.class);
	
	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;
	
	//@Autowired
	//private MessageService messageService;
	
	@PostMapping
	public void doNotify(@RequestBody Notification notification) {
		logger.debug("NOTIFICATION INCOMING - " + notification.getId() + " - " + notification.getSubject());
		//Message message = notification.toMessage();
		for(User user : notification.getGuests()) {
			this.simpMessagingTemplate.convertAndSendToUser(user.getUsername(), "/queue/private", notification);
		}
	}
}