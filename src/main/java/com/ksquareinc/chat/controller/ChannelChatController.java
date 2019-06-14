package com.ksquareinc.chat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.ksquareinc.chat.model.Message;
import com.ksquareinc.chat.model.OutputMessage;
import com.ksquareinc.chat.service.MessageService;

@Controller
public class ChannelChatController {
	
	@Autowired
	private MessageService messageService;
	
	/*
	@MessageMapping("/chat/{channel}")
	@SendTo("/topic/messages/{channel}")
	public OutputMessage send(@DestinationVariable("channel") String channel, MessagePr messagePr) {
		String time = new SimpleDateFormat("HH:mm").format(new Date());
	    return new OutputMessage(messagePr.getFrom(), messagePr.getText(), time, channel);
	}*/
	
	@MessageMapping("/chat/{channel}")
	@SendTo("/topic/messages/{channel}")
	public ResponseEntity<?> send(@DestinationVariable("channel") String channel, Message message) {
		Message newMessage = messageService.create(message);
		return ResponseEntity.ok().body(newMessage);
	}
}