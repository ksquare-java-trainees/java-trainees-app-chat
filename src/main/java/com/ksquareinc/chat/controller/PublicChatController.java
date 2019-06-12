package com.ksquareinc.chat.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.ksquareinc.chat.model.Message;
import com.ksquareinc.chat.model.OutputMessage;

@Controller
public class PublicChatController {
	
	@MessageMapping("/chat/public")
	@SendTo("/topic/messages/public")
	public OutputMessage send(Message message) {
		String time = new SimpleDateFormat("HH:mm").format(new Date());
	    return new OutputMessage(message.getFrom(), message.getText(), time, "public");
	}
}
