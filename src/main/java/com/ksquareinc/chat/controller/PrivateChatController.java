package com.ksquareinc.chat.controller;

import com.ksquareinc.chat.model.Message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class PrivateChatController {

	@Autowired
    private SimpMessagingTemplate simpMessagingTemplate;
	
	@MessageMapping("/send/message")
    public Message privateMessage(@Payload Message message){
        this.simpMessagingTemplate.convertAndSend("/topic/"+message.getTo()  ,message);
        this.simpMessagingTemplate.convertAndSend("/topic/"+message.getFrom(),message);
        return message;
    }
	
}
